package com.niladri.Online.food.ordering.repository.ingredient;

import com.niladri.Online.food.ordering.model.ingredient.IngredientModel;
import com.niladri.Online.food.ordering.model.ingredientCategory.IngredientCategoryModel;
import com.niladri.Online.food.ordering.model.resturant.ResturantModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IngredientRepository extends JpaRepository<IngredientModel, Long> {

	@Query("select im from IngredientModel im where im.resturant.resturantId = :resturantId" +
			" and lower(im.ingredientName) = lower(:ingredientName)" +
			" and im.ingredientCategory.ingredientCategoryId = :ingredientCategoryId")
	IngredientModel findIngredientByResturantAndIngredientCategoy(
			@Param("ingredientName") String ingredientName,
			@Param("resturantId") Long resturantId,
			@Param("ingredientCategoryId") Long ingredientCategoryId);

	List<IngredientModel> findByResturantResturantId(Long resturantId);
}
