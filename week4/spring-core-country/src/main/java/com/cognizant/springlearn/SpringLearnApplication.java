package com.cognizant.springlearn;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

@SpringBootApplication
public class SpringLearnApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(SpringLearnApplication.class);

	public static void main(String[] args) {
		LOGGER.info("===== Entering main() method of SpringLearnApplication =====");

		// This assignment demonstrates Spring Core (XML bean configuration),
		// so we read a bean from country.xml instead of starting the web server.
		displayCountry();

		LOGGER.info("===== Exiting main() method of SpringLearnApplication =====");
	}

	/**
	 * Loads the Spring XML configuration file country.xml, retrieves the
	 * "country" bean from the application context, and logs its details.
	 */
	public static void displayCountry() {
		LOGGER.info("Inside displayCountry()");

		// ClassPathXmlApplicationContext looks for country.xml on the classpath
		// (src/main/resources) and builds the Spring container from it.
		ApplicationContext context = new ClassPathXmlApplicationContext("country.xml");

		// getBean() returns the fully-constructed and property-populated bean
		// that Spring created while parsing the XML above.
		Country country = (Country) context.getBean("country", Country.class);

		LOGGER.debug("Country : {}", country.toString());

		// Close the context to release resources (it is also an AutoCloseable).
		((ClassPathXmlApplicationContext) context).close();
	}

}
