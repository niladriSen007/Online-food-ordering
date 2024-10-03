package com.niladri.Online.food.ordering.model.food;

import com.niladri.Online.food.ordering.model.foodCategory.FoodCategoryModel;
import com.niladri.Online.food.ordering.model.ingredient.IngredientModel;
import com.niladri.Online.food.ordering.model.resturant.ResturantModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "food")
public class FoodModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long foodId;
	private String foodName;
	private String foodDescription;
	private Integer foodPrice;

	@ManyToOne
	private List<FoodCategoryModel> foodCategory;

	@Column(length = 1000)
	@ElementCollection
	private String foodImages;

	private boolean foodAvailable;
	@ManyToOne
	private List<ResturantModel> restaurant;
	private boolean isVegetarian;
	private boolean isSeasonal;
	@ManyToMany
	private List<IngredientModel> ingredients = new ArrayList<>();
	private LocalDateTime creationDate;
}
