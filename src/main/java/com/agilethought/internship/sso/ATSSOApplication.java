package com.agilethought.internship.sso;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class ATSSOApplication {

	public static void main(String[] args) {
		SpringApplication.run(ATSSOApplication.class, args);
	}

}
