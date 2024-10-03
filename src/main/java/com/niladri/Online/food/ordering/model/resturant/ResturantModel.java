package com.niladri.Online.food.ordering.model.resturant;

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
@Table(name = "resturant")
public class ResturantModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long resturantId;
	private String resturantName;
	private String resturantDescription;
	private List<String> images;
}
