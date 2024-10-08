package com.niladri.Online.food.ordering.service.foodCategory.ifc;

import com.niladri.Online.food.ordering.model.foodCategory.FoodCategoryModel;

import java.util.List;

public interface FoodCategoryServiceInterface {
	FoodCategoryModel addCategory(FoodCategoryModel foodCategoryRequest, Long userId);

	List<FoodCategoryModel> getCategoriesByResturantId(Long resturantId);

	FoodCategoryModel getCategoryById(Long categoryId);

	String deleteCategory(Long categoryId);
}
