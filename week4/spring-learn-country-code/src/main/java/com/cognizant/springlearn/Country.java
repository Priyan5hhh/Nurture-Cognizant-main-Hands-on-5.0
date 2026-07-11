package com.cognizant.springlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * POJO / Spring bean representing a country. Instantiated by Spring from the
 * bean definitions in country.xml (no-arg constructor + setter injection).
 */
public class Country {

	private static final Logger LOGGER = LoggerFactory.getLogger(Country.class);

	private String code;
	private String name;

	public Country() {
		LOGGER.debug("Inside Country Constructor.");
	}

	public String getCode() {
		LOGGER.debug("Inside getCode()");
		return code;
	}

	public void setCode(String code) {
		LOGGER.debug("Inside setCode(), code = {}", code);
		this.code = code;
	}

	public String getName() {
		LOGGER.debug("Inside getName()");
		return name;
	}

	public void setName(String name) {
		LOGGER.debug("Inside setName(), name = {}", name);
		this.name = name;
	}

	@Override
	public String toString() {
		return "Country [code=" + code + ", name=" + name + "]";
	}
}
