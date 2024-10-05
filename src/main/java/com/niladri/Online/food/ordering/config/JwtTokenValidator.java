package com.niladri.Online.food.ordering.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.crypto.SecretKey;
import java.io.IOException;
import java.util.List;

@Slf4j
public class JwtTokenValidator extends OncePerRequestFilter {
	@Override
	protected void doFilterInternal(HttpServletRequest request,
	                                HttpServletResponse response,
	                                FilterChain filterChain) throws ServletException, IOException {
		
			String jwtHeader = request.getHeader(JwtConstants.JWT_HEADER);

			if(jwtHeader!=null){
				jwtHeader = jwtHeader.replace("Bearer ","");
				try{

//					The selected code is responsible for generating a `SecretKey` used to
//					sign and verify JWTs (JSON Web Tokens). Here's a brief explanation:

//					SecretKey secretKey = Keys.hmacShaKeyFor(JwtConstants.SECRET_KEY.getBytes());

//					1. **`Keys.hmacShaKeyFor`**: This method from the `io.jsonwebtoken.security.Keys`
//					class generates a `SecretKey` for HMAC-SHA algorithms.
//					2. **`JwtConstants.SECRET_KEY.getBytes()`**: This converts the secret
//					key string defined in `JwtConstants` to a byte array, which is required
//					by the `hmacShaKeyFor` method.

//					This `SecretKey` is then used to parse and validate JWTs in subsequent lines of the code.
					SecretKey secretKey = Keys.hmacShaKeyFor(JwtConstants.SECRET_KEY.getBytes());

//					The selected code is responsible for parsing a JWT (JSON Web Token)
//					and extracting its claims. Here's a brief explanation:
//					1. **Parser Builder Initialization**:
//					Claims claims = Jwts.parserBuilder()
//					This initializes a JWT parser builder.
//					2. **Setting the Signing Key**:
//							.setSigningKey(secretKey)
//					The parser is configured with the secret key used to sign the JWT.
//					3. **Building the Parser**:
//							.build()
//					This builds the JWT parser.
//					4. **Parsing the JWT**:
//							.parseClaimsJws(jwtHeader)
//					The JWT is parsed using the provided header, which contains the token.
//					5. **Extracting the Claims**:
//							.getBody();
//					The claims (payload) of the JWT are extracted and stored in the `claims` variable.

					Claims claims = Jwts.parserBuilder()
							.setSigningKey(secretKey)
							.build()
							.parseClaimsJws(jwtHeader)
							.getBody();
					log.info("Claims: {}",claims);


					String email = String.valueOf(claims.get("email"));
					String authorities = String.valueOf(claims.get("authorities"));

					log.debug("Authorities: {}",authorities);


//					The selected code is responsible for converting a comma-separated string
//					of authorities into a list of `GrantedAuthority` objects. This is useful
//					for setting up the user's roles and permissions in the Spring Security context.

//					List<GrantedAuthority> grantedAuthorities = AuthorityUtils
//							.commaSeparatedStringToAuthorityList(authorities);

//					- **`AuthorityUtils.commaSeparatedStringToAuthorityList(authorities)`**:
//					This method takes a comma-separated string (e.g., "ROLE_USER,ROLE_ADMIN")
//					and converts it into a list of `GrantedAuthority` objects. Each role or
//					authority in the string is transformed into a `GrantedAuthority`
//					object, which Spring Security uses to manage user roles and permissions.

					List<GrantedAuthority> grantedAuthorities = AuthorityUtils
							.commaSeparatedStringToAuthorityList(authorities);

//					The selected code is responsible for creating an `Authentication` object
//					using the extracted email and authorities from the JWT claims. This object
//					is then used to set the authentication in the Spring Security context.


//					Authentication authentication = new
//							UsernamePasswordAuthenticationToken(email, null, grantedAuthorities);

//					- **`UsernamePasswordAuthenticationToken`**: This is a Spring Security class
//					used to represent an authentication request or an authenticated principal
//									once the request has been processed.

//					- **`email`**: This is the principal (usually the username) extracted from the JWT claims.
//							- **`null`**: This is the credentials, which are not needed here as the JWT
//					has already been validated.
//							- **`grantedAuthorities`**: This is a list of authorities (roles/permissions)
//					extracted from the JWT claims.


					Authentication authentication = new
							UsernamePasswordAuthenticationToken(email,null,grantedAuthorities);

//					This `authentication` object is then set in the `SecurityContextHolder`
//					to establish the security context for the current request:

//					SecurityContextHolder.getContext().setAuthentication(authentication);

					SecurityContextHolder.getContext().setAuthentication(authentication);
				}catch (Exception e){
					throw new BadCredentialsException("Invalid Token");
				}
			}

//		The selected code, `filterChain.doFilter(request, response);`, is responsible for
//		continuing the filter chain in a Spring Boot application.Here's a brief explanation:

//				- **`filterChain.doFilter(request, response);`**: This method passes the
//		request and response to the next filter in the chain. If there are no more filters,
//		it forwards the request to the resource (e.g., a servlet or a controller).

//		In the context of the `JwtTokenValidator` class, this line ensures that after the
//		JWT token is validated and the security context is set, the request proceeds through
//		the remaining filters and eventually reaches the intended endpoint.

		filterChain.doFilter(request,response);

	}
}
