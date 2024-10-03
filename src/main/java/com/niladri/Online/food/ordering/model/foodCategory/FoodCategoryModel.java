package com.niladri.Online.food.ordering.model.foodCategory;

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
@Table(name = "food_category")
public class FoodCategoryModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long foodCategoryId;
	private String foodCategoryName;
	private String foodCategoryRestaurant;
}
