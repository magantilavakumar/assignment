package com.assignment.batch;
/**
 * @author magantilavakumar
 *
 */
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.assignment")
public class AssignmentApplication {

	private static final Logger log = LoggerFactory.getLogger(AssignmentApplication.class);
	public static void main(String[] args) {
		log.info("You are in TestApplicaiton");
		SpringApplication.run(AssignmentApplication.class, args);		
	}

}
