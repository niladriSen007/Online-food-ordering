package com.niladri.Online.food.ordering.config;


import com.niladri.Online.food.ordering.model.user.Roles;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class SpringSecurityConfiguration {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

		    http.sessionManagement(management ->
						    management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
							.authorizeHttpRequests(request -> request
//						    .requestMatchers("/api/v1/user/**").authenticated()
							.requestMatchers("/api/v1/public/**").permitAll()
							.requestMatchers("/api/v1/admin/**")
							.hasAnyAuthority(Roles.ADMIN.toString(),Roles.RESTURANT_OWNER.toString()).anyRequest().authenticated())
							.addFilterBefore(new JwtTokenValidator(), UsernamePasswordAuthenticationFilter.class)
							.csrf(csrf -> csrf.disable())
							.cors(cors -> cors.configurationSource(corsConfigurationSource()));
			return http.build();
	}

	private CorsConfigurationSource corsConfigurationSource() {
		return new CorsConfigurationSource() {
			@Override
			public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
				CorsConfiguration corsConfiguration = new CorsConfiguration();
				corsConfiguration.setAllowedOrigins(Arrays.asList(
						"http://localhost:3000",
						"http://localhost:4200"
						));
				corsConfiguration.setAllowedMethods(Collections.singletonList("*"));
				corsConfiguration.setAllowCredentials(true);
				corsConfiguration.setAllowedHeaders(Collections.singletonList("*"));
				corsConfiguration.setExposedHeaders(Arrays.asList("Authorization"));
				corsConfiguration.setMaxAge(3600L);


				return corsConfiguration;
			}
		};
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

}
