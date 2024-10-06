package com.niladri.Online.food.ordering.controller.user;

import com.niladri.Online.food.ordering.dto.response.auth.AuthResponseDto;
import com.niladri.Online.food.ordering.dto.user.UserResponseDto;
import com.niladri.Online.food.ordering.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

	@Autowired
	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping("/profile")
	public ResponseEntity<AuthResponseDto> getUserProfileByJwt(@RequestHeader("Authorization") String authorizationToken) {
		return ResponseEntity.ok(userService.getUserProfileByJwt(authorizationToken));
	}
}
