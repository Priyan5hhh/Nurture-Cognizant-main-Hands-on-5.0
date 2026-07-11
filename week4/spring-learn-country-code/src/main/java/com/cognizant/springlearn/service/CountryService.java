package com.cognizant.springlearn.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

import com.cognizant.springlearn.Country;

/**
 * Service that looks up a country by its ISO code from the Spring XML
 * configuration. The matching is case insensitive.
 */
@Service
public class CountryService {

	private static final Logger LOGGER = LoggerFactory.getLogger(CountryService.class);

	/**
	 * Loads the country list from country.xml and returns the country whose
	 * code matches the supplied code, ignoring case.
	 *
	 * @param code two-character ISO code (case insensitive)
	 * @return the matching Country
	 */
	@SuppressWarnings("unchecked")
	public Country getCountry(String code) {
		LOGGER.info("START getCountry() with code = {}", code);

		ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");
		List<Country> countryList = (List<Country>) context.getBean("countryList");

		// Case-insensitive match using a lambda / stream.
		Country country = countryList.stream()
				.filter(c -> c.getCode().equalsIgnoreCase(code))
				.findFirst()
				.orElse(null);

		LOGGER.debug("Matched Country : {}", country);
		LOGGER.info("END getCountry()");
		return country;
	}
}
