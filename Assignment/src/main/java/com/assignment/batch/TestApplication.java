package com.assignment.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TestApplication {

	private static final Logger log = LoggerFactory.getLogger(TestApplication.class);
	public static void main(String[] args) {
		log.info("You are in TestApplicaiton");
		SpringApplication.run(TestApplication.class, args);		
	}

}
