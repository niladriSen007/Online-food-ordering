package com.niladri.Online.food.ordering.service.ingredient.ifc;

import com.niladri.Online.food.ordering.dto.ingredient.IngredientRequestDto;
import com.niladri.Online.food.ordering.model.ingredient.IngredientModel;

import java.util.List;

public interface IngredientServiceInterface {
	IngredientModel createIngredient(IngredientRequestDto ingredientRequest);

	IngredientModel updateIngredientStock(Long ingredientId);

	List<IngredientModel> getIngredientsByResturantId(Long resturantId);
}
