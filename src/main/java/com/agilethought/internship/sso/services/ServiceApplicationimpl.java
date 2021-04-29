package com.agilethought.internship.sso.services;

import com.agilethought.internship.sso.dto.NewUserRequest;
import com.agilethought.internship.sso.validator.user.NewUserValidator;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import com.agilethought.internship.sso.dto.UserDTO;
import com.agilethought.internship.sso.mapper.UserMapping;
import com.agilethought.internship.sso.model.User;
import com.agilethought.internship.sso.dto.NewUserResponse;
import com.agilethought.internship.sso.repository.RepositoryApplication;

import java.util.ArrayList;
import java.util.List;

import static com.agilethought.internship.sso.services.PopulateFields.setLetterCases;

@Service
@Slf4j
public class ServiceApplicationimpl implements ServiceApplication {

	@Autowired
	private MapperFacade orikaMapperFacade;

	@Autowired
	private UserMapping userMapping;

	@Autowired
	private RepositoryApplication repositoryApplication;

	@Autowired
	private NewUserValidator newUserValidator;

	public NewUserResponse createUser(NewUserRequest request) {
		User user = orikaMapperFacade.map(request, User.class);
		newUserValidator.validate(user);
		setLetterCases(user);
		User savedUsers = repositoryApplication.save(user);
		log.info("ServiceApplicationimpl.createUser- User saved  successfully with id: {}", user.getId());
		return orikaMapperFacade.map(savedUsers, NewUserResponse.class);
	}

	@Override
	public List<UserDTO> getAllUsers() {
		log.info("ServiceApplicationimpl.getUsers - Before getting all the users");
		List<User> response = userRepository.findAll();
		log.info("ServiceApplicationimpl.getUsers -  Consulted successfully on mongoDB: {}", response);
		return orikaMapperFacade.mapAsList(response, UserDTO.class);
	}

	@Override
	public List<UserDTO> getUsersByEmail(String email) {
		log.info("ServiceApplicationimpl.getUsers - Searching users by email");
		List<User> users = repositoryApplication.findUserWithCredentials(email, "");
		log.info("ServiceApplicationimpl.getUsersByEmail - getUsersByEmail operation was successful: {}", users);
		return new ArrayList<>();
	}
}
