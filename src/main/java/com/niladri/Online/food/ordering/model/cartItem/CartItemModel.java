package com.niladri.Online.food.ordering.model.cartItem;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.niladri.Online.food.ordering.model.cart.CartModel;
import com.niladri.Online.food.ordering.model.food.FoodModel;
import com.niladri.Online.food.ordering.model.ingredient.IngredientModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cart_item_model")
public class CartItemModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cartItemId;
	@JsonIgnore
	@ManyToOne
	private CartModel cart;
	@ManyToOne
	private FoodModel food;
	private int quantity;
	//	@OneToMany
	private List<String> ingredients;
	private int totalPrice;
}
