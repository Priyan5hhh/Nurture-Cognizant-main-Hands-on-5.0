package com.cognizant.springlearn.controller;

import java.util.Base64;
import java.util.Collections;
import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

/**
 * Authentication controller.
 *
 * STEP 2: read the Authorization header and decode the username / password.
 * STEP 3: generate a JWT for the authenticated user and return it.
 */
@RestController
public class AuthenticationController {

	private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

	// Must be at least 256 bits (32 bytes) for the HS256 signing algorithm.
	private static final String SECRET_KEY = "mySecretKeyForJwtTokenGenerationSpringLearn2024";

	// Token validity: 20 minutes (in milliseconds).
	private static final long TOKEN_VALIDITY_MS = 20 * 60 * 1000L;

	/**
	 * Handles GET /authenticate. The credentials arrive in the Authorization
	 * header (HTTP Basic, sent by "curl -u user:pwd"). We decode the username
	 * and generate a JWT for it.
	 *
	 * @param authHeader the raw "Basic base64(user:pwd)" Authorization header
	 * @return a JSON object of the form {"token": "..."}
	 */
	@GetMapping("/authenticate")
	public Map<String, String> authenticate(@RequestHeader("Authorization") String authHeader) {
		LOGGER.info("START authenticate()");

		// STEP 2: decode the username (and password) from the Basic auth header.
		String username = getUser(authHeader);
		LOGGER.debug("Authenticated user = {}", username);

		// STEP 3: generate a token for that user.
		String token = generateToken(username);
		LOGGER.debug("Generated JWT = {}", token);

		LOGGER.info("END authenticate()");
		return Collections.singletonMap("token", token);
	}

	/**
	 * Decodes the Base64 "user:password" from the Basic Authorization header
	 * and returns the username portion.
	 */
	private String getUser(String authHeader) {
		LOGGER.info("START getUser()");

		// Header looks like: "Basic dXNlcjpwd2Q=" -> strip the "Basic " prefix.
		String base64Credentials = authHeader.substring("Basic ".length()).trim();
		String credentials = new String(Base64.getDecoder().decode(base64Credentials));

		// credentials = "username:password"
		String username = credentials.split(":", 2)[0];

		LOGGER.info("END getUser()");
		return username;
	}

	/**
	 * Builds a signed JWT whose subject is the username, issued now and
	 * expiring after TOKEN_VALIDITY_MS. Signed with HS256.
	 */
	private String generateToken(String username) {
		LOGGER.info("START generateToken()");

		long nowMillis = System.currentTimeMillis();
		SecretKey key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());

		String token = Jwts.builder()
				.subject(username)
				.issuedAt(new Date(nowMillis))
				.expiration(new Date(nowMillis + TOKEN_VALIDITY_MS))
				.signWith(key)
				.compact();

		LOGGER.info("END generateToken()");
		return token;
	}
}
