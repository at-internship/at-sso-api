package com.agilethought.internship.sso.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDatabaseFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultMongoTypeMapper;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.agilethought.internship.sso.model.User;
import com.agilethought.internship.sso.model.UserId;
import com.agilethought.internship.sso.services.ServiceApplication;

import antlr.collections.List;

@Configuration
@RestController
public class ControllerAplication {
	RestTemplate restTemplate; 
	
	@Autowired
	private MongoDatabaseFactory mongoDatabaseFactory;
	
	@Autowired
	private MongoMappingContext mongoMappingContext;
	
	@Autowired
	private ServiceApplication serviceApplication;
	
	@Bean
	public MappingMongoConverter mappingMongoConverter() {
		DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDatabaseFactory);
		MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mongoMappingContext);
		converter.setTypeMapper(new DefaultMongoTypeMapper(null));
		return converter;
	}
	
	@PostMapping(value = "/at-sso-api/api/v1/user", produces = "application/json")
	@ResponseStatus(value = HttpStatus.CREATED)
	public UserId createUser(@RequestBody User user) {
		UserId userId = serviceApplication.createUser(user);
		return userId;
	}
	
	@GetMapping(value="/at-sso-api/api/v1/user", produces = "application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public java.util.List<User> getAllUsers(){
		return serviceApplication.getUsers();
	}

}
