package com.niladri.Online.food.ordering.model.resturant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.niladri.Online.food.ordering.model.address.AddressModel;
import com.niladri.Online.food.ordering.model.contact.ContactModel;
import com.niladri.Online.food.ordering.model.food.FoodModel;
import com.niladri.Online.food.ordering.model.order.OrderModel;
import com.niladri.Online.food.ordering.model.review.ReviewModel;
import com.niladri.Online.food.ordering.model.user.UserModel;
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
@Table(name = "resturant_model")
public class ResturantModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long resturantId;
	private String resturantName;
	private String resturantDescription;
	private String cuisineType;
	@Embedded
	private ContactModel contactInformation;
	private int numRating;
	private LocalDateTime registrationDate;
	private boolean open;

	@OneToOne
	private UserModel owner;

	@ManyToOne
	private AddressModel address;

	private String openingHours;

	@JsonIgnore
	@OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<ReviewModel> reviews;

	@JsonIgnore
	@OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
	private List<OrderModel> orders = new ArrayList<>();

	@Column(length = 1000)
	@ElementCollection
	private List<String> resturantImages;

	@JsonIgnore
	@OneToMany(mappedBy = "restaurant", cascade = CascadeType.ALL)
	private List<FoodModel> foods = new ArrayList<>();

}