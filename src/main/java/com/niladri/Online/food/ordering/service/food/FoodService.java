package com.niladri.Online.food.ordering.service.food;

import com.niladri.Online.food.ordering.dto.food.FoodRequestDto;
import com.niladri.Online.food.ordering.exception.ResourceNotFound;
import com.niladri.Online.food.ordering.model.food.FoodModel;
import com.niladri.Online.food.ordering.model.resturant.ResturantModel;
import com.niladri.Online.food.ordering.repository.food.FoodRepository;
import com.niladri.Online.food.ordering.service.food.ifc.FoodServiceInterface;
import com.niladri.Online.food.ordering.service.resturant.ResturantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class FoodService implements FoodServiceInterface {

	@Autowired
	private FoodRepository foodRepository;

	@Autowired
	private ResturantService resturantService;

	@Override
	public FoodModel addFood(FoodRequestDto foodRequest, ResturantModel resturant) {
		String foodName = foodRequest.getFoodName();
		List<FoodModel> allFoods = resturant.getFoods();
		for (FoodModel food : allFoods) {
			if (food.getFoodName().equals(foodName)) {
				throw new IllegalArgumentException("Food with name " + foodName + " already exists");
			}
		}
		FoodModel food = new FoodModel();
		food.setFoodName(foodRequest.getFoodName());
		food.setFoodDescription(foodRequest.getFoodDescription());
		food.setFoodPrice(foodRequest.getFoodPrice());
		food.setFoodCategory(foodRequest.getFoodCategory());
		food.setFoodImages(foodRequest.getFoodImages());
		food.setSeasonal(foodRequest.isSeasonal());
		food.setVegetarian(foodRequest.isVegetarian());
		food.setCreationDate(LocalDateTime.now()) ;
		food.setIngredients(foodRequest.getIngredients());
		food.setResturant(resturant);
		FoodModel newFood = foodRepository.save(food);
		allFoods.add(newFood);
		resturant.setFoods(allFoods);

		return newFood;
	}

	@Override
	public String deleteFood(Long foodId) {
		FoodModel food = foodRepository.findById(foodId).orElse(null);
		if(food == null){
			throw new ResourceNotFound("Food" , "food id" , foodId.toString());
		}
		food.setResturant(null);
		foodRepository.delete(food);
		return food != null ? "Food deleted successfully" : "Food deletion failed";
	}

	@Override
	public FoodModel updateFood(Long foodId, FoodRequestDto foodRequest) {
		FoodModel food = foodRepository.findById(foodId).orElse(null);
		if(food == null){
			throw new ResourceNotFound("Food" , "food id" , foodId.toString());
		}
		food.setFoodName(foodRequest.getFoodName() != null ? foodRequest.getFoodName() : food.getFoodName());
		food.setFoodDescription(foodRequest.getFoodDescription() != null ? foodRequest.getFoodDescription() : food.getFoodDescription());
		food.setFoodPrice(foodRequest.getFoodPrice() != 0 ? foodRequest.getFoodPrice() : food.getFoodPrice());
		food.setFoodCategory(foodRequest.getFoodCategory() != null ? foodRequest.getFoodCategory() : food.getFoodCategory());
		food.setFoodImages(foodRequest.getFoodImages() != null ? foodRequest.getFoodImages() : food.getFoodImages());
		food.setSeasonal(foodRequest.isSeasonal() != food.isSeasonal() ? foodRequest.isSeasonal() : food.isSeasonal());
		food.setVegetarian(foodRequest.isVegetarian() != food.isVegetarian() ? foodRequest.isVegetarian() : food.isVegetarian());
		food.setIngredients(foodRequest.getIngredients() != null ? foodRequest.getIngredients() : food.getIngredients());
		return foodRepository.save(food);
	}

	@Override
	public List<FoodModel> getFoodsByResturantId(Long resturantId) {
		ResturantModel resturant = resturantService.findResturantById(resturantId);
		if(resturant == null){
			throw new ResourceNotFound("Resturant" , "id" , resturantId.toString());
		}
		List<FoodModel> allFoods = foodRepository.findByResturantResturantId(resturantId);
		return allFoods;
	}

	@Override
	public List<FoodModel> getFoodsByCategoryAndResturantId(Long resturantId, boolean vegetarian, boolean nonveg, boolean seasonal, String foodCategory) {
		List<FoodModel> allFoods = getFoodsByResturantId(resturantId);
		if(allFoods.isEmpty()){
			throw new ResourceNotFound("Food" , "resturant id" , resturantId.toString());
		}
		if(vegetarian){
			allFoods = filterFoodByCategory(allFoods, "Vegetarian");
		} else if (nonveg) {
			allFoods = filterFoodByCategory(allFoods, "Non-Vegetarian");
		}else if(seasonal){
			allFoods = filterFoodByCategory(allFoods, "Seasonal");
		} else{
			allFoods = filterFoodByCategory(allFoods, foodCategory);
		}
		return List.of();
	}

	@Override
	public String changeFoodStatus(Long foodId) {
		FoodModel food = foodRepository.findById(foodId).orElse(null);
		if(food == null){
			throw new ResourceNotFound("Food" , "food id" , foodId.toString());
		}
		food.setFoodAvailable(!food.isFoodAvailable());
		foodRepository.save(food);
		return food.isFoodAvailable() ? "Food is now available" : "Food is now unavailable";
	}

	@Override
	public FoodModel getFoodById(Long foodId) {
		FoodModel food = foodRepository.findById(foodId).orElse(null);
		if(food == null){
			throw new ResourceNotFound("Food" , "food id" , foodId.toString());
		}
		return food;
	}

	public List<FoodModel> filterFoodByCategory(List<FoodModel> allFoods, String foodCategory){
		switch (foodCategory){
			case "Vegetarian":
				return allFoods.stream().filter(food1 ->
						food1.isVegetarian()).toList();
			case "Non-Vegetarian":
				return allFoods.stream().filter(food ->
						!food.isVegetarian()).toList();
			case "Seasonal":
				return allFoods.stream().filter(food ->
						food.isSeasonal()).toList();
			default:
				if(foodCategory != null && foodCategory != ""){
					return allFoods.stream().filter(food ->
							food.getFoodCategory().getFoodCategoryName().equals(foodCategory)).toList();
				}else{
					return allFoods;
				}
		}
	}


}
