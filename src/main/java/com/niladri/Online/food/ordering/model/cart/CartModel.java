package com.niladri.Online.food.ordering.model.cart;


import com.niladri.Online.food.ordering.model.cartItem.CartItemModel;
import com.niladri.Online.food.ordering.model.food.FoodModel;
import com.niladri.Online.food.ordering.model.order.OrderModel;
import com.niladri.Online.food.ordering.model.user.UserModel;
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
@Table(name = "cart_model")
public class CartModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long cartId;

	@OneToOne
	private UserModel customer;

	@OneToMany(mappedBy ="cart", cascade = CascadeType.ALL,orphanRemoval = true)
	private List<CartItemModel> items = new ArrayList<>();

	private int totalAmount;
}