package com.niladri.Online.food.ordering.mapper;

import com.niladri.Online.food.ordering.dto.user.UserResponseDto;
import com.niladri.Online.food.ordering.model.user.UserModel;

public class UserMapper {
	public static UserResponseDto mapUserModelToUserResponseDto(UserModel userModel) {
		UserResponseDto userResponseDto = new UserResponseDto();
		userResponseDto.setUserId(userModel.getUserId());
		userResponseDto.setUserName(userModel.getUserName());
		userResponseDto.setEmail(userModel.getEmail());
		userResponseDto.setRole(userModel.getRole());
		return userResponseDto;
	}

	public static UserModel mapUserResponseDtoToUserModel(UserResponseDto userResponseDto) {
		UserModel userModel = new UserModel();
		userModel.setUserId(userResponseDto.getUserId());
		userModel.setUserName(userResponseDto.getUserName());
		userModel.setEmail(userResponseDto.getEmail());
		userModel.setRole(userResponseDto.getRole());
		return userModel;
	}
}
