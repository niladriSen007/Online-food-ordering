package com.niladri.Online.food.ordering.controller.admin.food;

import com.niladri.Online.food.ordering.dto.food.FoodRequestDto;
import com.niladri.Online.food.ordering.dto.response.auth.AuthResponseDto;
import com.niladri.Online.food.ordering.dto.resturant.ResturantRequestDto;
import com.niladri.Online.food.ordering.dto.user.UserResponseDto;
import com.niladri.Online.food.ordering.exception.ResourceNotFound;
import com.niladri.Online.food.ordering.model.food.FoodModel;
import com.niladri.Online.food.ordering.model.resturant.ResturantModel;
import com.niladri.Online.food.ordering.service.food.FoodService;
import com.niladri.Online.food.ordering.service.resturant.ResturantService;
import com.niladri.Online.food.ordering.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/food")
public class AdminFoodController {
	@Autowired
	private FoodService foodService;
	@Autowired
	private UserService userService;
	@Autowired
	private ResturantService resturantService;



	public UserResponseDto getUserProfile(String token){
		AuthResponseDto AuthResponseDto = userService.getUserProfileByJwt(token);
		UserResponseDto user = AuthResponseDto.getUser();
		return user;
	}

	@PostMapping("/add")
	public ResponseEntity<FoodModel> addFood(@RequestBody FoodRequestDto foodRequest,
	                                         @RequestHeader("Authorization") String token) {

		UserResponseDto user = getUserProfile(token);
		if(user == null){
			throw new ResourceNotFound("User" , "token" , token);
		}
		ResturantModel resturant = resturantService.findResturantById(foodRequest.getResturantId());
		if(resturant == null){
			throw new ResourceNotFound("Resturant" , "id" , foodRequest.getResturantId().toString());
		}
		return new ResponseEntity<>(foodService.addFood(foodRequest,resturant), HttpStatus.CREATED);
	}


	@DeleteMapping("/delete/{foodId}")
	public ResponseEntity<String> deleteFood(@PathVariable Long foodId,
	                                         @RequestHeader("Authorization") String token) {
		UserResponseDto user = getUserProfile(token);
		if(user == null){
			throw new ResourceNotFound("User" , "token" , token);
		}
		String message = foodService.deleteFood(foodId);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}


	@PutMapping("/update/{foodId}")
	public ResponseEntity<FoodModel> updateFood(@PathVariable Long foodId,@RequestBody FoodRequestDto foodRequest,
	                                           @RequestHeader("Authorization") String token) {
		UserResponseDto user = getUserProfile(token);
		if (user == null) {
			throw new ResourceNotFound("User", "token", token);
		}
		ResturantModel resturant = resturantService.findResturantById(foodRequest.getResturantId());
		if (resturant == null) {
			throw new ResourceNotFound("Resturant", "id", foodRequest.getResturantId().toString());
		}

		return new ResponseEntity<>(foodService.updateFood(foodId, foodRequest), HttpStatus.OK);
	}


	@PutMapping("/{foodId}/status")
	public ResponseEntity<String> changeFoodStatus(@PathVariable Long foodId,
	                                               @RequestHeader("Authorization") String token) {
		UserResponseDto user = getUserProfile(token);
		if (user == null) {
			throw new ResourceNotFound("User", "token", token);
		}
		return new ResponseEntity<>(foodService.changeFoodStatus(foodId), HttpStatus.OK);
	}
}
