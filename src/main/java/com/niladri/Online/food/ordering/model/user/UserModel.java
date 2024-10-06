package com.niladri.Online.food.ordering.model.user;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.niladri.Online.food.ordering.dto.resturant.ResturantResponseDto;
import com.niladri.Online.food.ordering.model.address.AddressModel;
import com.niladri.Online.food.ordering.model.order.OrderModel;
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
@Table(name = "user_model")
public class UserModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	private String userName;
	private String email;

	private String password;
	private Roles role = Roles.CUSTOMER;
	private String status;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
	private List<OrderModel> orders = new ArrayList<>();

	@ElementCollection
	private List<ResturantResponseDto> favorites = new ArrayList<>();

	@OneToMany(cascade = CascadeType.ALL,orphanRemoval = true)
	private List<AddressModel> addresses = new ArrayList<>();
}
