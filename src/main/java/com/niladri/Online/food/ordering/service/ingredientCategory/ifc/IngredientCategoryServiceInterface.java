package com.niladri.Online.food.ordering.service.ingredientCategory.ifc;

import com.niladri.Online.food.ordering.dto.ingredientCategory.IngredientCategoryRequestDto;
import com.niladri.Online.food.ordering.model.ingredientCategory.IngredientCategoryModel;

import java.util.List;

public interface IngredientCategoryServiceInterface {
	IngredientCategoryModel addIngredientCategory(IngredientCategoryRequestDto ingredientCategoryRequest);

	List<IngredientCategoryModel> getIngredientCategoryByResturantId(Long resturantId);
}
