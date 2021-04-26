package com.agilethought.internship.sso.services;

import com.agilethought.internship.sso.domain.NewUserRequest;
import com.agilethought.internship.sso.domain.NewUserResponse;
import com.agilethought.internship.sso.validator.user.CreateNewUserValidation;

import com.agilethought.internship.sso.domain.UpdateUserRequest;
import com.agilethought.internship.sso.domain.UpdateUserResponse;
import com.agilethought.internship.sso.validator.user.UpdateUserValidator;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.agilethought.internship.sso.domain.UserDTO;
import com.agilethought.internship.sso.mapper.UserTransformer;
import com.agilethought.internship.sso.model.User;
import com.agilethought.internship.sso.model.UserId;
import com.agilethought.internship.sso.repository.RepositoryApplication;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import java.util.List;

@Service
public class ServiceApplicationimpl implements ServiceApplication {
	private static final Logger logger = LogManager.getLogger();

	@Autowired
	private MapperFacade orikaMapperFacade;

	@Autowired
	private UserTransformer userTransformer;

	@Autowired
	private RepositoryApplication repositoryApplication;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private CreateNewUserValidation createNewUserValidation;

	@Autowired
	private UpdateUserValidator updateUserValidator;

	@Override
	public NewUserResponse createUser(NewUserRequest request) {
		UserId userId = new UserId();
		User user = orikaMapperFacade.map(request, User.class);
		createNewUserValidation.validate(user);
		setLetterCases(user);
		logger.info("ServiceApplicationimpl.createUser - users transformed: {}", user);
		User savedUser = repositoryApplication.save(user);
		logger.info("ServiceApplicationimpl.createUser- User saved  successfully with id: {}", user.getId());
		logger.info("ServiceApplicationimpl.createUser- User created successfully on mongoDB: {}", userId);
		return orikaMapperFacade.map(savedUser, NewUserResponse.class);
	}

	@Override
	public List<UserDTO> getUsers() {
		logger.info("ServiceApplicationimpl.getUsers - Before getting all the users");
		List<User> response = repositoryApplication.findAll();
		logger.info("ServiceApplicationimpl.getUsers -  Consulted successfully on mongoDB: {}", response);
		return orikaMapperFacade.mapAsList(response, UserDTO.class);
	}

	@Override
	public UpdateUserResponse updateUser(UpdateUserRequest request, String id) {
		request.setId(id);
		User userUpdatedFields = orikaMapperFacade.map(request, User.class);
		updateUserValidator.validate(userUpdatedFields);
		setLetterCases(userUpdatedFields);
		User updatedUser = repositoryApplication.save(userUpdatedFields);
		return orikaMapperFacade.map(updatedUser, UpdateUserResponse.class);

	}


	@Override
	public List<UserDTO> getUsersByEmail(String email) {
		logger.info("ServiceApplicationimpl.getUsers - Searching users by email");
		List<UserDTO> users = repositoryApplication.findUsersByEmail(email);
		logger.info("ServiceApplicationimpl.getUsersByEmail - getUsersByEmail operation was successful: {}", users);
		return users;
	}

	private static void setLetterCases(User request){
		request.setFirstName(request.getFirstName().toUpperCase());
		request.setLastName(request.getLastName().toUpperCase());
		request.setEmail(request.getEmail().toLowerCase());
	}
}
