package com.niladri.Online.food.ordering.service.food.ifc;

import com.niladri.Online.food.ordering.dto.food.FoodRequestDto;
import com.niladri.Online.food.ordering.model.food.FoodModel;
import com.niladri.Online.food.ordering.model.resturant.ResturantModel;

import java.util.List;

public interface FoodServiceInterface {
	FoodModel addFood(FoodRequestDto foodRequest, ResturantModel resturant);

	String deleteFood(Long foodId);

	FoodModel updateFood(Long foodId, FoodRequestDto foodRequest);

	List<FoodModel> getFoodsByResturantId(Long resturantId);

	List<FoodModel> getFoodsByCategoryAndResturantId(Long resturantId, boolean vegetarian, boolean nonveg, boolean seasonal, String foodCategory);

	String changeFoodStatus(Long foodId);

	FoodModel getFoodById(Long foodId);
}
