package com.niladri.Online.food.ordering.dto.resturant;

import com.niladri.Online.food.ordering.model.address.AddressModel;
import com.niladri.Online.food.ordering.model.contact.ContactModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResturantRequestDto {
	private String resturantName;
	private String resturantDescription;
	private String cuisineType;
	private ContactModel contactInformation;
	private LocalDateTime registrationDate;
	private AddressModel address;
	private List<String> resturantImages;
	private String openingHours;
}
