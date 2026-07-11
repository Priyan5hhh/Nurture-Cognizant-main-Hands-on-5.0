package com.cognizant.springlearn.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * REST controller exposing a simple "Hello World" endpoint.
 *
 * @RestController tells Spring this class handles web requests and that the
 * value returned by each method is written directly to the HTTP response body
 * (as opposed to being resolved to a view/template).
 */
@RestController
public class HelloController {

	private static final Logger LOGGER = LoggerFactory.getLogger(HelloController.class);

	/**
	 * Handles GET /hello and returns a hard-coded greeting.
	 *
	 * @return the text "Hello World!!"
	 */
	@GetMapping("/hello")
	public String sayHello() {
		LOGGER.info("START sayHello()");

		String message = "Hello World!!";

		LOGGER.info("END sayHello()");
		return message;
	}
}
