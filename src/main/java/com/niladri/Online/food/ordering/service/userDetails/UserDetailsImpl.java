package com.niladri.Online.food.ordering.service.userDetails;

import com.niladri.Online.food.ordering.model.user.Roles;
import com.niladri.Online.food.ordering.model.user.UserModel;
import com.niladri.Online.food.ordering.repository.user.UserRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class UserDetailsImpl implements UserDetailsService {

	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserModel user = userRepository.findByEmail(username);
		if (user == null) {
			throw new UsernameNotFoundException("User not found with email: " + username);
		}
		Roles role = user.getRole();
//		if(role == null) role = Roles.CUSTOMER;
		log.info("User found with email: " + username + " and role: " + role);

		List<GrantedAuthority> authorities =new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(role.toString()));

		return User.builder()
				.username(user.getEmail())
				.password(user.getPassword())
				.authorities(authorities)
				.build();
	}
}
