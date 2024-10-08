package com.niladri.Online.food.ordering.dto.food;

import com.niladri.Online.food.ordering.model.foodCategory.FoodCategoryModel;
import com.niladri.Online.food.ordering.model.ingredient.IngredientModel;
import lombok.Data;

import java.util.List;

@Data
public class FoodRequestDto {
	private String foodName;
	private String foodDescription;
	private int foodPrice;


	private FoodCategoryModel foodCategory;
	private List<String> foodImages;


	private Long resturantId;

	private boolean isVegetarian;
	private boolean isSeasonal;


	private List<IngredientModel> ingredients;

}
