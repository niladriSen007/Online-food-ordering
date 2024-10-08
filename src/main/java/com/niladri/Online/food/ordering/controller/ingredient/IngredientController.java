package com.niladri.Online.food.ordering.controller.ingredient;

import com.niladri.Online.food.ordering.model.ingredient.IngredientModel;
import com.niladri.Online.food.ordering.service.ingredient.IngredientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/public/ingredient")
public class IngredientController {

	@Autowired
	private IngredientService ingredientService;

	@GetMapping("/resturant/{resturantId}")
	public ResponseEntity<List<IngredientModel>> getIngredientsByResturantId(@PathVariable Long resturantId){
		return ResponseEntity.ok(ingredientService.getIngredientsByResturantId(resturantId));
	}
}
