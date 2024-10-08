package com.niladri.Online.food.ordering.controller.admin.ingredientCategory;

import com.niladri.Online.food.ordering.dto.ingredientCategory.IngredientCategoryRequestDto;
import com.niladri.Online.food.ordering.dto.response.auth.AuthResponseDto;
import com.niladri.Online.food.ordering.dto.user.UserResponseDto;
import com.niladri.Online.food.ordering.model.foodCategory.FoodCategoryModel;
import com.niladri.Online.food.ordering.model.ingredientCategory.IngredientCategoryModel;
import com.niladri.Online.food.ordering.service.ingredientCategory.IngredientCategoryService;
import com.niladri.Online.food.ordering.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/ingredientCategory")
public class AdminIngredientCategoryController {

	@Autowired
	private IngredientCategoryService ingredientCategoryService;
	@Autowired
	private UserService userService;

	public UserResponseDto getUserProfile(String token){
		AuthResponseDto AuthResponseDto = userService.getUserProfileByJwt(token);
		UserResponseDto user = AuthResponseDto.getUser();
		return user;
	}

	@PostMapping("/create")
	public ResponseEntity<IngredientCategoryModel> addIngredientCategory(@RequestBody IngredientCategoryRequestDto ingredientCategoryRequest,
	                                                                     @RequestHeader("Authorization") String token) {
		UserResponseDto user = getUserProfile(token);
		if (user == null) {
			throw new BadCredentialsException("User not authorized to update resturant");
		}
		return ResponseEntity.ok(ingredientCategoryService.addIngredientCategory(ingredientCategoryRequest));
	}
}
