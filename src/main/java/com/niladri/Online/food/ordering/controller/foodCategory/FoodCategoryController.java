package com.niladri.Online.food.ordering.controller.foodCategory;

import com.niladri.Online.food.ordering.model.foodCategory.FoodCategoryModel;
import com.niladri.Online.food.ordering.service.foodCategory.FoodCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/public/category")
public class FoodCategoryController {

	@Autowired
	private FoodCategoryService foodCategoryService;

	@GetMapping("/resturant/{resturantId}")
	public ResponseEntity<List<FoodCategoryModel>> getCategoriesByResturant(@PathVariable Long resturantId){
		return ResponseEntity.ok(foodCategoryService.getCategoriesByResturantId(resturantId));
	}


	@GetMapping("/{categoryId}")
	public ResponseEntity<FoodCategoryModel> getCategoryById(@PathVariable Long categoryId){
		return ResponseEntity.ok(foodCategoryService.getCategoryById(categoryId));
	}
}
