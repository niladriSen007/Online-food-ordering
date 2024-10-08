package com.niladri.Online.food.ordering.controller.resturant;

import com.niladri.Online.food.ordering.dto.resturant.ResturantResponseDto;
import com.niladri.Online.food.ordering.dto.user.UserResponseDto;
import com.niladri.Online.food.ordering.model.resturant.ResturantModel;
import com.niladri.Online.food.ordering.model.user.UserModel;
import com.niladri.Online.food.ordering.service.resturant.ResturantService;
import com.niladri.Online.food.ordering.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/public/resturant")
public class ResturantController {

	@Autowired
	private ResturantService resturantService;
	@Autowired
	private UserService userService;

	public ResturantController(ResturantService resturantService, UserService userService) {
		this.resturantService = resturantService;
		this.userService = userService;
	}

	@GetMapping("/search")
	public ResponseEntity<List<ResturantModel>> findResturantByName(@RequestParam String keyword) {
		return new ResponseEntity<>(resturantService.findResturantByName(keyword).size() ==0
				? new ArrayList<ResturantModel>()
				: resturantService.findResturantByName(keyword), HttpStatus.OK);

	}

	@GetMapping("/all")
	public ResponseEntity<List<ResturantModel>> findAllResturants() {
		return new ResponseEntity<>(resturantService.findAllResturants(), HttpStatus.OK);
	}


	@GetMapping("/get/{resturantId}")
	public ResponseEntity<ResturantModel> findResturantById(@PathVariable Long resturantId) {
		return new ResponseEntity<>(resturantService.findResturantById(resturantId), HttpStatus.OK);
	}

	@GetMapping("/get/owner/{ownerId}")
	public ResponseEntity<ResturantModel> findResturantByOwner(@PathVariable Long ownerId) {
		return new ResponseEntity<>(resturantService.findResturantByOwner(ownerId), HttpStatus.OK);
	}

	@PutMapping("/{resturantId}/add-to-favourites")
	public ResponseEntity<String> addToFavourites(@PathVariable Long resturantId,
	                                                            @RequestHeader("Authorization") String token) {
		UserResponseDto user = userService.getUserProfileByJwt(token).getUser();
		if (user == null) {
			throw new BadCredentialsException("You need to login first to add resturants to favourites");
		}
		return new ResponseEntity<>(resturantService.addToFavourites(resturantId, user), HttpStatus.OK);
	}





}
