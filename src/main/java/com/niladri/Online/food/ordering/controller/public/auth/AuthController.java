package com.niladri.Online.food.ordering.controller.auth;

import com.niladri.Online.food.ordering.config.JwtProvider;
import com.niladri.Online.food.ordering.dto.response.auth.AuthResponseDto;
import com.niladri.Online.food.ordering.dto.response.auth.LoginRequestDto;
import com.niladri.Online.food.ordering.model.user.UserModel;
import com.niladri.Online.food.ordering.repository.user.UserRepository;
import com.niladri.Online.food.ordering.service.user.UserService;
import com.niladri.Online.food.ordering.service.userDetails.UserDetailsImpl;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/public")
public class AuthController {

	private UserRepository userRepository;
	private PasswordEncoder passwordEncoder;
	private UserDetailsImpl userDetails;
	private JwtProvider jwtProvider;
	private UserService userService;

	public AuthController(UserRepository userRepository,
	                      PasswordEncoder passwordEncoder,
	                      UserDetailsImpl userDetails,
	                      JwtProvider jwtProvider,
	                      UserService userService) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
		this.userDetails = userDetails;
		this.jwtProvider = jwtProvider;
		this.userService = userService;
	}



	@PostMapping("/register")
	public ResponseEntity<AuthResponseDto> registerUserController(@Valid @RequestBody UserModel userModel) {
		return ResponseEntity.ok(userService.registerUser(userModel));
	}

	@PostMapping("/login")
	public ResponseEntity<AuthResponseDto> loginUserController(@RequestBody LoginRequestDto loginRequestDto) {
		return ResponseEntity.ok(userService.loginUser(loginRequestDto));
	}
}
