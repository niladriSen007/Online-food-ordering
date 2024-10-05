package com.niladri.Online.food.ordering.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.*;

@Service
public class JwtProvider {
	SecretKey secretKey = Keys.hmacShaKeyFor(JwtConstants.SECRET_KEY.getBytes());

	public String generateToken(Authentication authentication) {
//		The selected line of code: is retrieving the collection of authorities (or roles)
//		granted to the authenticated user.
//				- `authentication.getAuthorities()` returns a collection of `GrantedAuthority`
//		objects, which represent the roles or permissions assigned to the user.
//				- The `Collection<? extends GrantedAuthority>` type ensures that the collection
//		can hold any object that is a subtype of `GrantedAuthority`.

//		This is typically used in Spring Security to manage and check user roles and permissions.
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		String authoritiesCSV = populateAuthoritiesInCSV(authorities);

//		The selected code is responsible for generating a JWT (JSON Web Token) for the
//		authenticated user. Here's a brief explanation:
//		1. **JWT Builder Initialization**:
//		Jwts.builder()
//		This initializes a JWT builder.
//		2. **Setting the Issued At Time**:
//		.setIssuedAt(new Date())
//		This sets the time at which the JWT was issued.
//		3. **Setting the Expiration Time**:
//		.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
//		This sets the expiration time of the JWT to 10 hours after the current time.
//		4. **Setting the Authorities Claim**:
//		.claim("authorities", authoritiesCSV)
//		This adds a custom claim to the JWT with the user's authorities (roles) as a comma-separated string.
//		5. **Setting the Email Claim**:
//		.claim("email", authentication.getName())
//		This adds a custom claim to the JWT with the user's email address.
//		6. **Signing the JWT**:
//		.signWith(secretKey)
//		This signs the JWT using the secret key.
//		7. **Compacting the JWT**:
//		.compact()
//		This generates the final JWT as a compact, URL-safe string.
		return Jwts.builder()
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
				.claim("authorities", authoritiesCSV)
				.claim("email", authentication.getName())
				.signWith(secretKey)
				.compact();

	}


	public String getEmailFromToken(String jwtHeader) {
		jwtHeader = jwtHeader.replace("Bearer ", "");

//		The selected code is responsible for parsing a JWT (JSON Web Token) and extracting
//		the email claim from its payload. Here's a brief explanation:
//		1. **Parsing the JWT**:
//		Jwts.parserBuilder()
//		This initializes a JWT parser builder.
//		2. **Setting the Signing Key**:
//		.setSigningKey(secretKey)
//		The parser is configured with the secret key used to sign the JWT.
//		3. **Building the Parser**:
//		.build()
//		This builds the JWT parser.
//		4. **Parsing the JWT**:
//		.parseClaimsJws(jwtHeader)
//		The JWT is parsed using the provided header, which contains the token.
//		5. **Extracting the Claims**:
//		.getBody();
//		The claims (payload) of the JWT are extracted and stored in the `claims` variable.
		Claims claims = Jwts.parserBuilder()
				.setSigningKey(secretKey)
				.build()
				.parseClaimsJws(jwtHeader)
				.getBody();

//		The selected code is responsible for extracting the email claim from the JWT claims
//		and returning it as a string. This email claim is typically used to identify the
//		authenticated user.
		return String.valueOf(claims.get("email"));
	}

	public String populateAuthoritiesInCSV(Collection<? extends GrantedAuthority> authorities) {
//		The selected code is responsible for converting a collection of `GrantedAuthority`
//		objects to a comma-separated string of authorities. Here's a brief explanation:

//		1. **`Set<String>` Initialization**:
//		A `Set` is used to store unique authorities. The `HashSet` implementation is used
//		to ensure that duplicate authorities are not added.
		Set<String> authoritySet = new HashSet<>();

//		2. **Iterating Over the Collection**:
//		The code iterates over the collection of `GrantedAuthority` objects to extract
//		the authority names.
		for(GrantedAuthority authority: authorities){
			authoritySet.add(authority.getAuthority());
		}

//		3. **Joining Authorities**:
//		The `String.join` method is used to concatenate the authorities with a comma
//		delimiter. This creates a single string with all the authorities separated by commas.
		return String.join(",", authoritySet);

	}
}
