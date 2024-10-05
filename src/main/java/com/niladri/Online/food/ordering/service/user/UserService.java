package com.niladri.Online.food.ordering.service.user;

import com.niladri.Online.food.ordering.config.JwtProvider;
import com.niladri.Online.food.ordering.dto.response.auth.AuthResponseDto;
import com.niladri.Online.food.ordering.dto.response.auth.LoginRequestDto;
import com.niladri.Online.food.ordering.dto.user.UserResponseDto;
import com.niladri.Online.food.ordering.exception.UserAlreadyExists;
import com.niladri.Online.food.ordering.mapper.UserMapper;
import com.niladri.Online.food.ordering.model.cart.CartModel;
import com.niladri.Online.food.ordering.model.user.Roles;
import com.niladri.Online.food.ordering.model.user.UserModel;
import com.niladri.Online.food.ordering.repository.cart.CartRepository;
import com.niladri.Online.food.ordering.repository.user.UserRepository;
import com.niladri.Online.food.ordering.service.user.ifc.UserServiceInterface;
import com.niladri.Online.food.ordering.service.userDetails.UserDetailsImpl;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
@Slf4j
public class UserService implements UserServiceInterface {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtProvider jwtProvider;
	@Autowired
	private CartRepository cartRepository;
	@Autowired
	private UserDetailsImpl userDetailsImpl;



	@Override
	public AuthResponseDto registerUser(UserModel userModel) {
		System.out.println("registerUser - {}"+ userRepository.findByEmail(userModel.getEmail()));
		String email = userModel.getEmail();
		if (userRepository.findByEmail(email) != null) {
			throw new UserAlreadyExists("User already exists");
		}
		String password = userModel.getPassword();
		String encodedPassword = passwordEncoder.encode(password);
		Roles role = userModel.getRole();
		String userName = userModel.getUserName();

		UserModel newUser = new UserModel();
		newUser.setEmail(email);
		newUser.setPassword(encodedPassword);
		newUser.setRole(role);
		newUser.setUserName(userName);

		UserModel savedUser = userRepository.save(newUser);

		CartModel cartModel = new CartModel();
		cartModel.setCustomer(savedUser);
		CartModel savedCart = cartRepository.save(cartModel);

		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(new SimpleGrantedAuthority(role.toString()));

		Authentication authentication = new UsernamePasswordAuthenticationToken(email,password, authorities);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = jwtProvider.generateToken(authentication);

		UserResponseDto userResponseDto = new UserResponseDto();
		userResponseDto.setUserId(savedUser.getUserId());
		userResponseDto.setEmail(savedUser.getEmail());
		userResponseDto.setRole(savedUser.getRole());
		userResponseDto.setUserName(savedUser.getUserName());


		AuthResponseDto authResponse = new AuthResponseDto();
		authResponse.setJwtToken(token);
		authResponse.setMessage("User registered successfully");
		authResponse.setUser(userResponseDto);

		return authResponse;
	}



	@Override
	public AuthResponseDto loginUser(LoginRequestDto loginRequestDto) {
		String email = loginRequestDto.getEmail();
		String password = loginRequestDto.getPassword();

		Authentication authentication = authenticate(email, password);
		System.out.println("loginUser - {}"+ authentication);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		String token = jwtProvider.generateToken(authentication);
		AuthResponseDto authResponse = new AuthResponseDto();

		UserModel user = userRepository.findByEmail(email);
		UserResponseDto userResponseDto = UserMapper.mapUserModelToUserResponseDto(user);


		authResponse.setJwtToken(token);
		authResponse.setMessage("User logged in successfully");
		authResponse.setUser(userResponseDto);


		return authResponse;
	}

	@Override
	public AuthResponseDto getUserProfile(String authorizationToken) {
		String email = jwtProvider.getEmailFromToken(authorizationToken);
		UserModel user = userRepository.findByEmail(email);
		if(user == null){
			throw new BadCredentialsException("User not found with email: " + email);
		}
		UserResponseDto userResponseDto =  UserMapper.mapUserModelToUserResponseDto(user);
		AuthResponseDto authResponseDto = new AuthResponseDto();
		authResponseDto.setUser(userResponseDto);
		authResponseDto.setMessage("User Profile details fetched successfully");
		authResponseDto.setJwtToken("You don't need to see the token boss :). Be happy with your details only");
		return authResponseDto;
	}


	private Authentication authenticate(String email, String password) {

		UserDetails loginUserDetails = userDetailsImpl.loadUserByUsername(email);
		log.info("sign in userDetails - {}", loginUserDetails);

		if(loginUserDetails == null){
			log.error("User not found with email: {}" , email);
			throw new BadCredentialsException("Invalid username or password");
		}
		if(!passwordEncoder.matches(password, loginUserDetails.getPassword())){
			log.error("Invalid password for email: {}", email);
			throw new BadCredentialsException("Invalid username or password");
		}

//		The selected code creates an `Authentication` object using the
//		`UsernamePasswordAuthenticationToken` class. This object is used to represent the
//		authenticated user in the Spring Security context.

//		- `loginUserDetails`: This is an instance of `UserDetails` that contains the user's
//		   information, such as username and password.
//		- `null`: This parameter is for the credentials, which are not needed after authentication.
//		- `loginUserDetails.getAuthorities()`: This retrieves the user's roles or authorities.

//		This `Authentication` object is then returned to be set in the security context,
//		indicating that the user is authenticated with the provided details and authorities.
		return new UsernamePasswordAuthenticationToken(loginUserDetails,
				null, loginUserDetails.getAuthorities());
	}





}
