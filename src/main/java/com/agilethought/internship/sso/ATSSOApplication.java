package com.agilethought.internship.sso;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;


@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ATSSOApplication {

	public static final Logger logger = LogManager.getLogger(ATSSOApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(ATSSOApplication.class, args);
	}

}
