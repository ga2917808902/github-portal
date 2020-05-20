package com.portal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.portal.Repositories.IBookRepository;


@SpringBootApplication
public class Portal1Application {

	private static final Logger LOGGER = LoggerFactory.getLogger(Portal1Application.class);
	
	public static void main(String[] args) {
		SpringApplication.run(Portal1Application.class, args);
		
		LOGGER.error("Message logged at ERROR level");
		LOGGER.warn("Message logged at WARN level");
		LOGGER.info("Message logged at INFO level");
		LOGGER.debug("Message logged at DEBUG level");
	}

}
