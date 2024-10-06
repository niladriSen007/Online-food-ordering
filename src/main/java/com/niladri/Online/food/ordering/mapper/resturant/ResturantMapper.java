package com.niladri.Online.food.ordering.mapper.resturant;

import com.niladri.Online.food.ordering.dto.resturant.ResturantResponseDto;
import com.niladri.Online.food.ordering.model.resturant.ResturantModel;

public class ResturantMapper {
	public static ResturantResponseDto mapResturantModelToResturantResponseDto(ResturantModel resturantModel) {
		ResturantResponseDto resturantResponseDto = new ResturantResponseDto();
		resturantResponseDto.setResturantId(resturantModel.getResturantId());
		resturantResponseDto.setResturantName(resturantModel.getResturantName());
		resturantResponseDto.setResturantDescription(resturantModel.getResturantDescription());
		resturantResponseDto.setCuisineType(resturantModel.getCuisineType());

		return resturantResponseDto;
	}

	public static ResturantModel mapResturantResponseDtoToResturantModel(ResturantResponseDto resturantResponseDto) {
		ResturantModel resturantModel = new ResturantModel();
		resturantModel.setResturantId(resturantResponseDto.getResturantId());
		resturantModel.setResturantName(resturantResponseDto.getResturantName());
		resturantModel.setResturantDescription(resturantResponseDto.getResturantDescription());
		resturantModel.setCuisineType(resturantResponseDto.getCuisineType());
		return resturantModel;
	}
}
