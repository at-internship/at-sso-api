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

import com.agilethought.internship.sso.ATSSOApplication;
import com.agilethought.internship.sso.exception.BadRequestException;
import com.agilethought.internship.sso.model.User;
import com.agilethought.internship.sso.model.UserId;
import com.agilethought.internship.sso.services.BusinessValidations;
import com.agilethought.internship.sso.services.ServiceApplication;
import com.agilethougth.intership.sso.errorhandling.HttpExceptionMessage;
import com.agilethougth.intership.sso.errorhandling.PathErrorMessage;

import java.util.List;

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
	
	@PostMapping(value = "/api/v1/user", produces = "application/json")
	@ResponseStatus(value = HttpStatus.CREATED)
	public UserId createUser(@RequestBody User user) {
		
		UserId userId =  new UserId();
		
		if (!BusinessValidations.EmptyName(user.getName())) {
			if(!BusinessValidations.EmptyFirstName(user.getFirstName())) {
				if(!BusinessValidations.WrongEmail(user.getEmail())) {
				    if(!BusinessValidations.EmptyPassword(user.getPassword())) {
					    if(!BusinessValidations.InvalidStatus(user.getStatus())) {
					    	    List<User> users=serviceApplication.getUsersByEmail(user.getEmail());
					    	    if(users.isEmpty())
					    		userId = serviceApplication.createUser(user);
					    	    else throw new BadRequestException(HttpExceptionMessage.BadRequestMailAlreadyExists,PathErrorMessage.pathApi,HttpStatus.BAD_REQUEST);
					    	}					    		
					    }
					}
				}
			}			
		else ATSSOApplication.logger.info("Error");
		return userId;		 
	}//End createUser	
	
	@GetMapping(value="/api/v1/user", produces = "application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public List<User> getAllUsers(){
		return serviceApplication.getUsers();
	}
}