package com.niladri.Online.food.ordering.repository.ingredientCategory;

import com.niladri.Online.food.ordering.model.ingredientCategory.IngredientCategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IngredientCategoryRepository extends JpaRepository<IngredientCategoryModel, Long> {

	@Query("select icm from IngredientCategoryModel icm where icm.resturant.resturantId = :resturantId and lower(icm.ingredientCategoryName) = lower(:ingredientCategoryName)")
	IngredientCategoryModel findByIngredientCategoryName(@Param("ingredientCategoryName") String ingredientCategoryName,
	                                     @Param("resturantId") Long resturantId);

	IngredientCategoryModel findByIngredientCategoryId(Long ingredientCategoryId);

	List<IngredientCategoryModel> findByResturantResturantId(Long resturantId);
}
