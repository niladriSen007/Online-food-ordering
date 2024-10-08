package com.niladri.Online.food.ordering.dto.ingredient;

import lombok.Data;

@Data
public class IngredientRequestDto {
	private Long resturantId;
	private String ingredientName;
	private Long ingredientCategoryId;
}
