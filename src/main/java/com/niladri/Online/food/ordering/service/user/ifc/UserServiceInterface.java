package com.niladri.Online.food.ordering.service.user.ifc;

import com.niladri.Online.food.ordering.dto.response.auth.AuthResponseDto;
import com.niladri.Online.food.ordering.dto.response.auth.LoginRequestDto;
import com.niladri.Online.food.ordering.model.user.UserModel;

public interface UserServiceInterface {
	AuthResponseDto registerUser(UserModel userModel);

	AuthResponseDto loginUser(LoginRequestDto loginRequestDto);

	AuthResponseDto getUserProfile(String authorizationToken);
}
