package com.niladri.Online.food.ordering.model.review;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.niladri.Online.food.ordering.model.resturant.ResturantModel;
import com.niladri.Online.food.ordering.model.user.UserModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "review_model")
public class ReviewModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reviewId;

	@ManyToOne
	private UserModel customer;

	@ManyToOne
	@JsonIgnore
	private ResturantModel resturant;

	private String message;

	private double rating;

	private LocalDateTime createdAt;
}