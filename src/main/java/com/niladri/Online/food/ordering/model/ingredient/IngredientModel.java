package com.niladri.Online.food.ordering.model.ingredient;

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
@Table(name = "ingredient")
public class IngredientModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long ingredientId;
	private String ingredientName;
	@ManyToOne
	private String ingredientCategory;
}
