package com.niladri.Online.food.ordering.repository.user;

import com.niladri.Online.food.ordering.model.user.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserModel,Long> {
}
