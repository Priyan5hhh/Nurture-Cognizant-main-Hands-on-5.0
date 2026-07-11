package com.cognizant.springlearn.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.springlearn.Country;
import com.cognizant.springlearn.service.CountryService;

/**
 * REST controller for country lookups.
 */
@RestController
public class CountryController {

	private static final Logger LOGGER = LoggerFactory.getLogger(CountryController.class);

	@Autowired
	private CountryService countryService;

	/**
	 * Handles GET /countries/{code} and returns the matching country.
	 * The {code} path variable is matched case insensitively by the service.
	 *
	 * @param code ISO country code from the URL path
	 * @return the matching Country, serialized to JSON
	 */
	@GetMapping("/countries/{code}")
	public Country getCountry(@PathVariable String code) {
		LOGGER.info("START getCountry() with code = {}", code);

		Country country = countryService.getCountry(code);

		LOGGER.info("END getCountry()");
		return country;
	}
}
