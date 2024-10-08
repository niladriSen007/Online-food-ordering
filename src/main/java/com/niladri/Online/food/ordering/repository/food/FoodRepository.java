package com.niladri.Online.food.ordering.repository.food;

import com.niladri.Online.food.ordering.model.food.FoodModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FoodRepository extends JpaRepository<FoodModel, Long> {
	List<FoodModel> findByResturantResturantId(Long resturantId);
}
