package com.cognizant.springlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Simple POJO / Spring bean that represents a country supported by the
 * airlines website. It holds the two-character ISO code and the display name.
 *
 * The bean is instantiated by Spring from the definition in country.xml.
 * Spring uses the no-argument constructor and then calls the setters for each
 * &lt;property&gt; declared in the XML (this is "setter injection").
 */
public class Country {

	private static final Logger LOGGER = LoggerFactory.getLogger(Country.class);

	// Two-character ISO code, e.g. "IN"
	private String code;

	// Display name, e.g. "India"
	private String name;

	/**
	 * No-argument constructor. Spring requires this to create the bean before
	 * it can call the setters for setter injection.
	 */
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
