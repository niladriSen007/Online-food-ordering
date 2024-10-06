package com.niladri.Online.food.ordering.controller.admin.resturant;

import com.niladri.Online.food.ordering.dto.response.auth.AuthResponseDto;
import com.niladri.Online.food.ordering.dto.resturant.ResturantResponseDto;
import com.niladri.Online.food.ordering.dto.resturant.ResturantRequestDto;
import com.niladri.Online.food.ordering.dto.user.UserRequestDto;
import com.niladri.Online.food.ordering.dto.user.UserResponseDto;
import com.niladri.Online.food.ordering.model.resturant.ResturantModel;
import com.niladri.Online.food.ordering.model.user.UserModel;
import com.niladri.Online.food.ordering.service.resturant.ResturantService;
import com.niladri.Online.food.ordering.service.user.UserService;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/resturant")
public class AdminResturantController {

	@Autowired
	private ResturantService resturantService;

	@Autowired
	private UserService userService;

	public AdminResturantController(ResturantService resturantService, UserService userService) {
		this.resturantService = resturantService;
		this.userService = userService;
	}

	public UserResponseDto getUserProfile(String token){
		AuthResponseDto AuthResponseDto = userService.getUserProfileByJwt(token);
		UserResponseDto user = AuthResponseDto.getUser();
		return user;
	}


	@Schema(name="Resturant create API ",description = "Create Resturant")
	@PostMapping("/create")
	public ResponseEntity<Object> createResturant(
			 @RequestBody ResturantRequestDto resturantRequest,
	        @RequestHeader("Authorization") String token) {
		UserResponseDto user = getUserProfile(token);
		if (user != null) {
			return new ResponseEntity<>(resturantService.createResturant(resturantRequest, user)
										, HttpStatus.CREATED);
		}
		return ResponseEntity.badRequest().body("User not found");
	}


	@Schema(name="Resturant update API ",description = "Update Resturant")
	@PutMapping("/update/{retsurantId}")
	public ResponseEntity<ResturantModel> updateResturant(@PathVariable Long retsurantId,@Valid @RequestBody ResturantRequestDto resturantRequest,
	                                      @RequestHeader("Authorization") String token) {
		UserResponseDto user = getUserProfile(token);
		if (user == null) {
			throw new BadCredentialsException("User not authorized to update resturant");
		}
		return new ResponseEntity<>(resturantService.updateResturant(retsurantId,resturantRequest)
									, HttpStatus.OK);
	}

	@Schema(name="Resturant delete API ",description = "Delete Resturant")
	@DeleteMapping("/delete/{retsurantId}")
	public ResponseEntity<Object> deleteResturant(@PathVariable Long retsurantId,
	                                      @RequestHeader("Authorization") String token) {
		UserResponseDto user = getUserProfile(token);
		if (user == null) {
			throw new BadCredentialsException("User not authorized to update resturant");
		}
		resturantService.deleteResturant(retsurantId);
		return new ResponseEntity<>("Resturant deleted successfully", HttpStatus.OK);
	}

	@PutMapping("/{resturantId}/status")
	public ResponseEntity<String> changeResturantStatus(@PathVariable Long resturantId,
	                                      @RequestHeader("Authorization") String token) {
		UserResponseDto user = getUserProfile(token);
		if (user == null) {
			throw new BadCredentialsException("User not authorized to update resturant status");
		}
		return new ResponseEntity<>(resturantService.changeResturantStatus(resturantId)
									, HttpStatus.OK);
	}

}
