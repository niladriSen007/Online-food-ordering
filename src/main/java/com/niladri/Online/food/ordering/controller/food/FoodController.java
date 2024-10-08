package com.niladri.Online.food.ordering.controller.food;

import com.niladri.Online.food.ordering.model.food.FoodModel;
import com.niladri.Online.food.ordering.service.food.FoodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/public/food")
public class FoodController {
	@Autowired
	private FoodService foodService;


	@GetMapping("/foods/{resturantId}/all")
	public ResponseEntity<List<FoodModel>> getFoods(@PathVariable Long resturantId) {
		return new ResponseEntity<>(foodService.getFoodsByResturantId(resturantId), HttpStatus.OK);
	}

	@GetMapping("/foods/{resturantId}/filter")
	public ResponseEntity<List<FoodModel>> getFoodsByCategory(@PathVariable Long resturantId,
	                                                          @RequestParam boolean vegetarian,
	                                                          @RequestParam boolean seasonal,
	                                                          @RequestParam boolean nonveg,
	                                                          @RequestParam(required = false) String food_category) {
		return new ResponseEntity<>(foodService.getFoodsByCategoryAndResturantId(resturantId,
				vegetarian,nonveg,seasonal,food_category), HttpStatus.OK);
	}

	@GetMapping("/foods/{foodId}")
	public ResponseEntity<FoodModel> getFood(@PathVariable Long foodId) {
		return new ResponseEntity<>(foodService.getFoodById(foodId), HttpStatus.OK);
	}

}
