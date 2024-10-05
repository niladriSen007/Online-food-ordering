package com.niladri.Online.food.ordering.dto.response.auth;

import com.niladri.Online.food.ordering.dto.user.UserResponseDto;
import com.niladri.Online.food.ordering.model.user.Roles;
import lombok.Data;

@Data
public class AuthResponseDto {
	private String message;
	private String jwtToken;
	private UserResponseDto user;
}
