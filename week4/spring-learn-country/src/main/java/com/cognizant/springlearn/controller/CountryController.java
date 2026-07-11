package com.cognizant.springlearn.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.springlearn.Country;

/**
 * REST controller that returns India's country details.
 *
 * The Country object is loaded from the Spring XML configuration (country.xml)
 * and returned directly. Because this is a @RestController, Spring serializes
 * the returned object to JSON automatically.
 */
@RestController
public class CountryController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);

	/**
	 * Handles GET /country. Loads the "country" bean (India) from the Spring
	 * XML configuration and returns it.
	 *
	 * @return the India Country bean, serialized to JSON by Spring
	 */
	@RequestMapping("/country")
	public Country getCountryIndia() {
		LOGGER.info("START getCountryIndia()");

		ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
		Country country = context.getBean("country", Country.class);

		LOGGER.debug("Country : {}", country.toString());
		LOGGER.info("END getCountryIndia()");
		return country;
	}
}
