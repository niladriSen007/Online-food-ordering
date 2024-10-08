package com.niladri.Online.food.ordering.service.foodCategory;

import com.niladri.Online.food.ordering.exception.FoodCategoryAlreadyExists;
import com.niladri.Online.food.ordering.exception.ResourceNotFound;
import com.niladri.Online.food.ordering.model.foodCategory.FoodCategoryModel;
import com.niladri.Online.food.ordering.model.resturant.ResturantModel;
import com.niladri.Online.food.ordering.repository.foodCategory.FoodCategoryRepository;
import com.niladri.Online.food.ordering.service.foodCategory.ifc.FoodCategoryServiceInterface;
import com.niladri.Online.food.ordering.service.resturant.ResturantService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class FoodCategoryService implements FoodCategoryServiceInterface {

	@Autowired
	private ResturantService resturantService;
	@Autowired
	private FoodCategoryRepository foodCategoryRepository;



	@Override
	public FoodCategoryModel addCategory(FoodCategoryModel foodCategoryRequest, Long userId) {
		ResturantModel resturantModel = resturantService.findResturantByOwner(userId);
		if(resturantModel == null){
			throw new ResourceNotFound("Resturant" , "owner id" , userId.toString());
		}
		if(foodCategoryRepository.findByFoodCategoryName(foodCategoryRequest.getFoodCategoryName()) != null){
			throw new FoodCategoryAlreadyExists("Food Category already exist");
		}
		FoodCategoryModel foodCategoryModel = new FoodCategoryModel();
		foodCategoryModel.setFoodCategoryName(foodCategoryRequest.getFoodCategoryName());
		foodCategoryModel.setResturant(resturantModel);
		return foodCategoryRepository.save(foodCategoryModel);
	}

	@Override
	public List<FoodCategoryModel> getCategoriesByResturantId(Long resturantId) {
		ResturantModel resturantModel = resturantService.findResturantById(resturantId);
		if(resturantModel == null){
			throw new ResourceNotFound("Resturant" , "resturant id" , resturantId.toString());
		}
		return foodCategoryRepository.findByResturantResturantId(resturantId);
//		return null;
	}


	@Override
	public FoodCategoryModel getCategoryById(Long categoryId) {
		FoodCategoryModel foodCategoryModel = foodCategoryRepository.findById(categoryId).orElse(null);
		if(foodCategoryModel == null){
			throw new ResourceNotFound("Food Category" , "category id" , categoryId.toString());
		}
		return foodCategoryModel;

	}

	@Override
	public String deleteCategory(Long categoryId) {
		FoodCategoryModel foodCategoryModel = foodCategoryRepository.findById(categoryId).orElse(null);
		if(foodCategoryModel == null){
			throw new ResourceNotFound("Food Category" , "food category id" , categoryId.toString());
		}
		foodCategoryRepository.delete(foodCategoryModel);
		return "Food Category deleted successfully";
	}


}
