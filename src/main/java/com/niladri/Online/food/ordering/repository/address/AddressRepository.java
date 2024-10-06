package com.niladri.Online.food.ordering.repository.address;

import com.niladri.Online.food.ordering.model.address.AddressModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<AddressModel, Long> {
}
