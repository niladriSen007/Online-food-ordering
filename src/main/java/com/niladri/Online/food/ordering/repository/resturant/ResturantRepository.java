package com.niladri.Online.food.ordering.repository.resturant;

import com.niladri.Online.food.ordering.model.resturant.ResturantModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResturantRepository extends JpaRepository<ResturantModel, Long> {

	@Query("SELECT r FROM ResturantModel r WHERE lower(r.resturantName) LIKE lower(%?1%) OR lower(r.resturantDescription) LIKE lower(%?1%)")
	List<ResturantModel> findBySearchWord(String keyword);

	ResturantModel findByOwnerUserId(Long ownerId);
}
