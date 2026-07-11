package com.cognizant.springlearn.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

/**
 * Spring Security configuration.
 *
 * STEP 1 of the JWT process: secure the /authenticate endpoint with HTTP Basic
 * so that the credentials supplied by "curl -u user:pwd" are validated before
 * the controller runs.
 */
@Configuration
public class SecurityConfig {

	/**
	 * An in-memory user matching the credentials used in the assignment
	 * (user / pwd). {noop} means the password is stored in plain text (no
	 * password encoder) which is fine for this learning exercise.
	 */
	@Bean
	public InMemoryUserDetailsManager userDetailsService() {
		UserDetails user = User.withUsername("user")
				.password("{noop}pwd")
				.roles("USER")
				.build();
		return new InMemoryUserDetailsManager(user);
	}

	/**
	 * Every request must be authenticated, and authentication is done via HTTP
	 * Basic. CSRF is disabled because this is a stateless REST endpoint called
	 * from tools like curl / Postman.
	 */
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.csrf(csrf -> csrf.disable())
			.authorizeHttpRequests(auth -> auth.anyRequest().authenticated())
			.httpBasic(Customizer.withDefaults());
		return http.build();
	}
}
