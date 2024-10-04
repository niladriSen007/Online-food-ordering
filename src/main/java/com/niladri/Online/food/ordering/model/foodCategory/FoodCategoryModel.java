package com.niladri.Online.food.ordering.model.foodCategory;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "food_category_model")
public class FoodCategoryModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long foodCategoryId;

	private String foodCategoryName;

	@JsonIgnore
	@ManyToOne
	private ResturantModel Restaurant;


}