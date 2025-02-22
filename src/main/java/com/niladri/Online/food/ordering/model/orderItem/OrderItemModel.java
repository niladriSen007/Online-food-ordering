package com.niladri.Online.food.ordering.model.orderItem;

import com.niladri.Online.food.ordering.model.food.FoodModel;
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
@Table(name = "order_item_model")
public class OrderItemModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderItemId;
	@ManyToOne
	private FoodModel food;
	private int quantity;
	private int totalPrice;
	private List<String> ingredients;


}