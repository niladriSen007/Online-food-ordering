package com.niladri.Online.food.ordering.service.ingredientCategory;

import com.niladri.Online.food.ordering.dto.ingredientCategory.IngredientCategoryRequestDto;
import com.niladri.Online.food.ordering.exception.IngredientCategoryAlreadyExists;
import com.niladri.Online.food.ordering.exception.ResourceNotFound;
import com.niladri.Online.food.ordering.model.ingredientCategory.IngredientCategoryModel;
import com.niladri.Online.food.ordering.model.resturant.ResturantModel;
import com.niladri.Online.food.ordering.repository.ingredientCategory.IngredientCategoryRepository;
import com.niladri.Online.food.ordering.service.ingredientCategory.ifc.IngredientCategoryServiceInterface;
import com.niladri.Online.food.ordering.service.resturant.ResturantService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class IngredientCategoryService implements IngredientCategoryServiceInterface {

	@Autowired
	private IngredientCategoryRepository ingredientCategoryRepository;
	@Autowired
	private ResturantService resturantService;

	@Override
	public IngredientCategoryModel addIngredientCategory(IngredientCategoryRequestDto
			                                                         ingredientCategoryRequest) {
		IngredientCategoryModel categoryExistInResturantOrNot = ingredientCategoryRepository.
				findByIngredientCategoryName(ingredientCategoryRequest.getIngredientCategoryName(),
						ingredientCategoryRequest.getResturantId());

		if (categoryExistInResturantOrNot != null) {
			throw new IngredientCategoryAlreadyExists("Ingredient Category already exist in the resturant");
		}

		ResturantModel resturantModel = resturantService.findResturantById(ingredientCategoryRequest.getResturantId());
		if(resturantModel == null){
			throw new ResourceNotFound("Resturant", "Resturant Id", ingredientCategoryRequest.getResturantId());
		}
		IngredientCategoryModel ingredientCategoryModel = new IngredientCategoryModel();
		ingredientCategoryModel.setIngredientCategoryName(ingredientCategoryRequest.getIngredientCategoryName());
		ingredientCategoryModel.setResturant(resturantModel);
		return ingredientCategoryRepository.save(ingredientCategoryModel);

	}

	@Override
	public List<IngredientCategoryModel> getIngredientCategoryByResturantId(Long resturantId) {
		ResturantModel resturantModel = resturantService.findResturantById(resturantId);
		if(resturantModel == null){
			throw new ResourceNotFound("Resturant", "Resturant Id", resturantId);
		}
		return ingredientCategoryRepository.findByResturantResturantId(resturantId);
	}
}
