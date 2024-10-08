package com.niladri.Online.food.ordering.controller.admin.foodCategory;

import com.niladri.Online.food.ordering.dto.response.auth.AuthResponseDto;
import com.niladri.Online.food.ordering.dto.resturant.ResturantRequestDto;
import com.niladri.Online.food.ordering.dto.user.UserResponseDto;
import com.niladri.Online.food.ordering.model.foodCategory.FoodCategoryModel;
import com.niladri.Online.food.ordering.service.foodCategory.FoodCategoryService;
import com.niladri.Online.food.ordering.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/category")
public class AdminFoodCategoryController {

	@Autowired
	private FoodCategoryService foodCategoryService;

	@Autowired
	private UserService userService;



	public UserResponseDto getUserProfile(String token){
		AuthResponseDto AuthResponseDto = userService.getUserProfileByJwt(token);
		UserResponseDto user = AuthResponseDto.getUser();
		return user;
	}

	@PostMapping("/create")
	public ResponseEntity<FoodCategoryModel> addCategory(@RequestBody FoodCategoryModel foodCategoryRequest,
	                                  @RequestHeader("Authorization") String token) {
		UserResponseDto user = getUserProfile(token);
		if (user == null) {
			throw new BadCredentialsException("User not authorized to update resturant");
		}
		return ResponseEntity.ok(foodCategoryService.addCategory(foodCategoryRequest,user.getUserId()));
	}


	@DeleteMapping("/delete/{categoryId}")
	public ResponseEntity<String> deleteCategory(@PathVariable Long categoryId,
	                                  @RequestHeader("Authorization") String token) {
		UserResponseDto user = getUserProfile(token);
		if (user == null) {
			throw new BadCredentialsException("User not authorized to update resturant");
		}
		foodCategoryService.deleteCategory(categoryId);
		return ResponseEntity.ok("Category deleted successfully");
	}
}
