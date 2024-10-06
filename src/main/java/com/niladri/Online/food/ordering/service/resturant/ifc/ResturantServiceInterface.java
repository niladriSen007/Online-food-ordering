package com.niladri.Online.food.ordering.service.resturant.ifc;

import com.niladri.Online.food.ordering.dto.resturant.ResturantRequestDto;
import com.niladri.Online.food.ordering.dto.resturant.ResturantResponseDto;
import com.niladri.Online.food.ordering.dto.user.UserResponseDto;
import com.niladri.Online.food.ordering.model.resturant.ResturantModel;
import jakarta.validation.Valid;

import java.util.List;

public interface ResturantServiceInterface {
	List<ResturantModel> findResturantByName(String keyword);

	ResturantResponseDto createResturant(@Valid ResturantRequestDto resturantRequest, UserResponseDto user);

	ResturantModel updateResturant(Long resturantId,ResturantRequestDto resturantRequest);

	void deleteResturant(Long retsurantId);

	List<ResturantModel> findAllResturants();

	ResturantModel findResturantById(Long resturantId);

	ResturantModel findResturantByOwner(Long ownerId);

	String addToFavourites(Long resturantId, UserResponseDto user);

	String changeResturantStatus(Long resturantId);
}
