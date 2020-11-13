package com.agilethought.internship.sso.controller;

import lombok.extern.slf4j.Slf4j;
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
import com.agilethought.internship.sso.domain.UserDTO;
import com.agilethought.internship.sso.model.UserId;
import com.agilethought.internship.sso.services.BusinessValidations;
import com.agilethought.internship.sso.services.ServiceApplication;
import com.agilethougth.intership.sso.errorhandling.HttpExceptionMessage;
import com.agilethougth.intership.sso.errorhandling.PathErrorMessage;
import java.util.List;

@Configuration
@RestController
@Slf4j
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
		log.info("ControllerAplication.mappingMongoConverter - mappingMongoConverter was successfull", converter);
		return converter;
	}	
	
	@PostMapping(value = "/api/v1/user", produces = "application/json")
	@ResponseStatus(value = HttpStatus.CREATED)
	public UserId createUser(@RequestBody UserDTO userDTO) {
		log.info("ControllerAplication.createUser - user requested: {}", userDTO);
		UserId userId =  new UserId();
		log.info("ControllerAplication.createUser - userid created: {}", userId);
		
		if (!BusinessValidations.EmptyName(userDTO.getName())) {
			if(!BusinessValidations.EmptyFirstName(userDTO.getFirstName())) {
				if(!BusinessValidations.WrongEmail(userDTO.getEmail())) {
				    if(!BusinessValidations.EmptyPassword(userDTO.getPassword())) {
					    if(!BusinessValidations.InvalidStatus(userDTO.getStatus())) {
					    		userDTO.setEmail(userDTO.getEmail().toLowerCase().trim());
					    	    List<UserDTO> users=serviceApplication.getUsersByEmail(userDTO.getEmail());
					    	    if(users.isEmpty())
					    		userId = serviceApplication.createUser(userDTO);
					    	    else throw new BadRequestException(HttpExceptionMessage.BadRequestMailAlreadyExists,PathErrorMessage.pathApi,HttpStatus.BAD_REQUEST);
					    	}					    		
					    }
					}
				}
			}			
		else ATSSOApplication.logger.info("Error");
		log.info("ControllerAplication.createUser - POST operation was successful: {}", userId);
		return userId;		 
	}//End createUser	
	
	@GetMapping(value="/api/v1/user", produces = "application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public List<UserDTO> getAllUsers() {
		log.info("ControllerAplication.getAllUsers - Calling get operation");
		return serviceApplication.getUsers();
	}
}