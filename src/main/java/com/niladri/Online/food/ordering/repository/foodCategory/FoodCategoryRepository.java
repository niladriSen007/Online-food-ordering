package com.niladri.Online.food.ordering.repository.foodCategory;

import com.niladri.Online.food.ordering.model.foodCategory.FoodCategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodCategoryRepository extends JpaRepository<FoodCategoryModel, Long> {


	List<FoodCategoryModel> findByResturantResturantId(Long resturantId);

	FoodCategoryModel findByFoodCategoryName(String foodCategoryName);
}
