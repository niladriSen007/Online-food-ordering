package com.niladri.Online.food.ordering.service.ingredient;

import com.niladri.Online.food.ordering.dto.ingredient.IngredientRequestDto;
import com.niladri.Online.food.ordering.exception.IngredientAlreadyExists;
import com.niladri.Online.food.ordering.exception.ResourceNotFound;
import com.niladri.Online.food.ordering.model.ingredient.IngredientModel;
import com.niladri.Online.food.ordering.model.ingredientCategory.IngredientCategoryModel;
import com.niladri.Online.food.ordering.model.resturant.ResturantModel;
import com.niladri.Online.food.ordering.repository.ingredient.IngredientRepository;
import com.niladri.Online.food.ordering.repository.ingredientCategory.IngredientCategoryRepository;
import com.niladri.Online.food.ordering.service.ingredient.ifc.IngredientServiceInterface;
import com.niladri.Online.food.ordering.service.ingredientCategory.IngredientCategoryService;
import com.niladri.Online.food.ordering.service.resturant.ResturantService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;

@Service
@AllArgsConstructor
public class IngredientService implements IngredientServiceInterface {

	@Autowired
	private IngredientRepository ingredientRepository;
	@Autowired
	private ResturantService resturantService;
	@Autowired
	private IngredientCategoryRepository ingredientCategoryRepository;

	@Override
	public IngredientModel createIngredient(IngredientRequestDto ingredientRequest) {
		ResturantModel resturantModel = resturantService.findResturantById(ingredientRequest.getResturantId());
		if(resturantModel == null){
			throw new ResourceNotFound("Resturant" , "resturant id" , ingredientRequest.getResturantId().toString());
		}
		IngredientCategoryModel ingredientCategoryModel = ingredientCategoryRepository.findByIngredientCategoryId(ingredientRequest.getIngredientCategoryId());
		if(ingredientCategoryModel == null){
			throw new ResourceNotFound("Ingredient Category" , "category id" , ingredientRequest.getIngredientCategoryId().toString());
		}
		//check if ingredient already exists in the resturant
		IngredientModel ingredientModelExist = ingredientRepository.
				findIngredientByResturantAndIngredientCategoy(
						ingredientRequest.getIngredientName(),
				ingredientRequest.getResturantId(), ingredientRequest.getIngredientCategoryId());

		if(ingredientModelExist != null){
			throw new IngredientAlreadyExists("Ingredient with ingredient name" + ingredientRequest.getIngredientName() + " already exists in the resturant");
		}

		IngredientModel ingredientModel = new IngredientModel();
		ingredientModel.setIngredientName(ingredientRequest.getIngredientName());
		ingredientModel.setIngredientCategory(ingredientCategoryModel);
		ingredientModel.setResturant(resturantModel);
		IngredientModel savedIngredient = ingredientRepository.save(ingredientModel);
		ingredientCategoryModel.getIngredients().add(savedIngredient);
		return savedIngredient;
	}

	@Override
	public IngredientModel updateIngredientStock(Long ingredientId) {
		IngredientModel ingredientModel = ingredientRepository.findById(ingredientId).orElse(null);
		if(ingredientModel == null){
			throw new ResourceNotFound("Ingredient" , "ingredient id" , ingredientId.toString());
		}
		ingredientModel.setInStock(!ingredientModel.isInStock());
		ingredientRepository.save(ingredientModel);
		return ingredientModel;
	}

	@Override
	public List<IngredientModel> getIngredientsByResturantId(Long resturantId) {
		ResturantModel resturantModel = resturantService.findResturantById(resturantId);
		if(resturantModel == null){
			throw new ResourceNotFound("Resturant" , "resturant id" , resturantId.toString());
		}
		List<IngredientModel> ingredientModels = ingredientRepository.findByResturantResturantId(resturantId);
		if(ingredientModels == null || ingredientModels.size() == 0){
			throw new ResourceNotFound("Ingredient" , "resturant id" , resturantId.toString());
		}
		return ingredientModels;
	}


}
