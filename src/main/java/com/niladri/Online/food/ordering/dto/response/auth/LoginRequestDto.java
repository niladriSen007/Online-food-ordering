package com.niladri.Online.food.ordering.dto.response.auth;

import lombok.Data;

@Data
public class LoginRequestDto {
	private String email;
	private String password;
}
