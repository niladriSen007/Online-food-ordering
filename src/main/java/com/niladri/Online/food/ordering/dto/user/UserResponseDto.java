package com.niladri.Online.food.ordering.dto.user;

import com.niladri.Online.food.ordering.model.user.Roles;
import lombok.Data;

@Data
public class UserResponseDto {
	private Long userId;
	private String userName;
	private String email;
	private Roles role;

}
