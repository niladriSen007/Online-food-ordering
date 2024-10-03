package com.niladri.Online.food.ordering.model.address;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "address")
public class AddressModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long addressId;
	private String address;
	private String city;
	private String state;
	private String country;
	private String zipCode;
	private String phone;
	private String email;
	private String status;
}
