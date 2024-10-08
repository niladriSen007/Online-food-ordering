package com.niladri.Online.food.ordering.controller.ingredientCategory;

import com.niladri.Online.food.ordering.model.ingredientCategory.IngredientCategoryModel;
import com.niladri.Online.food.ordering.service.ingredientCategory.IngredientCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/public/ingredientCategory")
public class IngredientCategoryController {

	@Autowired
	private IngredientCategoryService ingredientCategoryService;

	@GetMapping("/resturant/{resturantId}/category")
	public ResponseEntity<List<IngredientCategoryModel>> getIngredientCategoryByResturantId(@PathVariable Long resturantId){
		return ResponseEntity.ok(ingredientCategoryService.getIngredientCategoryByResturantId(resturantId));
	}
}
