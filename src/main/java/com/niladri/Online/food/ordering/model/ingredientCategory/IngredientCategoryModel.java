package com.niladri.Online.food.ordering.model.ingredientCategory;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ingredient_category")
public class IngredientCategoryModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ingredientCategoryId;
	private String ingredientCategoryName;
//	private restaurant;
//	ingredients;
}
