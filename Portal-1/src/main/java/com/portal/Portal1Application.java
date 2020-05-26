package com.portal;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Portal1Application {

	private static final Logger LOGGER = LoggerFactory.getLogger(Portal1Application.class);

	public static void main(String[] args) throws IOException {
		SpringApplication.run(Portal1Application.class, args);
		
	}

}
