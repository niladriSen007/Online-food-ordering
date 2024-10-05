package com.niladri.Online.food.ordering.repository.cart;

import com.niladri.Online.food.ordering.model.cart.CartModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<CartModel, Long> {
}
