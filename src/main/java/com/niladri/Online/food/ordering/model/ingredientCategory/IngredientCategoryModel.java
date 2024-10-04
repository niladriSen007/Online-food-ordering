package com.niladri.Online.food.ordering.model.ingredientCategory;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.niladri.Online.food.ordering.model.ingredient.IngredientModel;
import com.niladri.Online.food.ordering.model.resturant.ResturantModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "ingredient_category_model")
public class IngredientCategoryModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ingredientCategoryId;

	private String ingredientCategoryName;

	@JsonIgnore
	@ManyToOne
	private ResturantModel restaurant;

	@OneToMany(mappedBy = "ingredientCategory", cascade = CascadeType.ALL)
	private List<IngredientModel> ingredients = new ArrayList<>();
}