package com.niladri.Online.food.ordering.dto.resturant;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.util.List;

@Data
@Embeddable
public class ResturantResponseDto {

	private Long resturantId;
	private String resturantName;
	private String resturantDescription;
	private String cuisineType;
	@Column(length = 1000)
	private List<String> images;


}
