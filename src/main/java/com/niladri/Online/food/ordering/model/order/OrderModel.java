package com.niladri.Online.food.ordering.model.order;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.niladri.Online.food.ordering.model.address.AddressModel;
import com.niladri.Online.food.ordering.model.orderItem.OrderItemModel;
import com.niladri.Online.food.ordering.model.resturant.ResturantModel;
import com.niladri.Online.food.ordering.model.user.UserModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_model")
public class OrderModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long orderId;
	private Long totalAmount;
	private String orderStatus;
	private LocalDateTime createdAt;
	//	private Payment payment;
	private int totalItem;
	private int totalPrice;

	@ManyToOne
	private UserModel customer;

	@JsonIgnore
	@ManyToOne
	private ResturantModel restaurant;

	@ManyToOne
	private AddressModel deliveryAddress;

	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
	private List<OrderItemModel> items;

}