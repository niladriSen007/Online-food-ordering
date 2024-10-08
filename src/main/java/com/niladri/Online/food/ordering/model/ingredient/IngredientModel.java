package com.niladri.Online.food.ordering.model.ingredient;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.niladri.Online.food.ordering.model.ingredientCategory.IngredientCategoryModel;
import com.niladri.Online.food.ordering.model.resturant.ResturantModel;
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
@Table(name = "ingredient_model")
public class IngredientModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ingredientId;
	private String ingredientName;
	@ManyToOne
	private IngredientCategoryModel ingredientCategory;
	@JsonIgnore
	@ManyToOne
	private ResturantModel resturant;
	private boolean inStock=true;
}