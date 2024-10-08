package com.niladri.Online.food.ordering.controller.admin.ingredient;

import com.niladri.Online.food.ordering.dto.ingredient.IngredientRequestDto;
import com.niladri.Online.food.ordering.dto.response.auth.AuthResponseDto;
import com.niladri.Online.food.ordering.dto.user.UserResponseDto;
import com.niladri.Online.food.ordering.exception.ResourceNotFound;
import com.niladri.Online.food.ordering.model.ingredient.IngredientModel;
import com.niladri.Online.food.ordering.service.ingredient.IngredientService;
import com.niladri.Online.food.ordering.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/admin/ingredient")
public class AdminIngredientController {
	@Autowired
	private IngredientService ingredientService;

	@Autowired
	private UserService userService;

	public UserResponseDto getUserProfile(String token){
		AuthResponseDto AuthResponseDto = userService.getUserProfileByJwt(token);
		UserResponseDto user = AuthResponseDto.getUser();
		return user;
	}

	@PostMapping("/create")
	public ResponseEntity<IngredientModel> createIngredient(@RequestBody IngredientRequestDto ingredientRequest,
	                                                        @RequestHeader("Authorization") String token) {
		UserResponseDto user = getUserProfile(token);
		if (user == null) {
			throw new BadCredentialsException("User not authorized to update resturant");
		}
		return ResponseEntity.ok(ingredientService.createIngredient(ingredientRequest));
	}

	@PutMapping("/update/ingredient/stock/{ingredientId}")
	public ResponseEntity<IngredientModel> updateIngredientStock(@PathVariable Long ingredientId,  @RequestHeader("Authorization") String token) {
		UserResponseDto user = getUserProfile(token);
		if (user == null) {
			throw new BadCredentialsException("User not authorized to update resturant");
		}
		return ResponseEntity.ok(ingredientService.updateIngredientStock(ingredientId));
	}
}
