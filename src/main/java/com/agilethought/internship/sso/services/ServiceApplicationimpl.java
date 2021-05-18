package com.agilethought.internship.sso.services;

import com.agilethought.internship.sso.exception.NotFoundException;
import com.agilethought.internship.sso.dto.*;
import com.agilethought.internship.sso.exception.UnauthorizedException;
import com.agilethought.internship.sso.validator.Validator;
import com.agilethought.internship.sso.validator.user.NewUserValidator;
import com.agilethought.internship.sso.validator.user.UpdateUserValidator;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;
import com.agilethought.internship.sso.mapper.UserMapping;
import com.agilethought.internship.sso.model.User;
import com.agilethought.internship.sso.repository.RepositoryApplication;
import static com.agilethought.internship.sso.exception.errorhandling.ErrorMessage.NOT_FOUND_RESOURCE;
import static com.agilethought.internship.sso.exception.errorhandling.ErrorMessage.USER;
import static com.agilethought.internship.sso.services.PopulateFields.setLetterCases;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.agilethought.internship.sso.dto.LoginRequest;
import com.agilethought.internship.sso.dto.NewUserRequest;
import com.agilethought.internship.sso.dto.NewUserResponse;
import com.agilethought.internship.sso.dto.UpdateUserRequest;
import com.agilethought.internship.sso.dto.UpdateUserResponse;
import com.agilethought.internship.sso.dto.UserDTO;
import com.agilethought.internship.sso.model.OAuth2AuthenticationAccessToken;
import com.agilethought.internship.sso.repository.AccessTokenRepository;

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

	@Autowired
	private UpdateUserValidator updateUserValidator;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private Validator<LoginRequest> loginValidator;
	
	@Autowired
	private AccessTokenRepository tokenRepository;

	public NewUserResponse createUser(NewUserRequest request) {
		User user = orikaMapperFacade.map(request, User.class);
		newUserValidator.validate(user);
		setLetterCases(user);
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		User savedUsers = repositoryApplication.save(user);
		log.info("ServiceApplicationimpl.createUser- User saved  successfully with id: {}", user.getId());
		return orikaMapperFacade.map(savedUsers, NewUserResponse.class);
	}

	@Override
	public List<UserDTO> getAllUsers() {
		log.info("ServiceApplicationimpl.getUsers - Before getting all the users");
		List<User> response = repositoryApplication.findAll();
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
	
	@Override
	public void deleteUserById(String id) {

		Optional<User> userFoundById = repositoryApplication.findById(id);
		if (!userFoundById.isPresent())
			throw new NotFoundException(
					String.format(NOT_FOUND_RESOURCE, USER, id)
			);
		repositoryApplication.deleteById(id);

	}
	
	@Override
	public UpdateUserResponse updateUserById(UpdateUserRequest request, String id) {
		request.setId(id);
		User userUpdatedFields = orikaMapperFacade.map(request, User.class);
		updateUserValidator.validate(userUpdatedFields);
		setLetterCases(userUpdatedFields);
		userUpdatedFields.setPassword(passwordEncoder.encode(request.getPassword()));
		User updatedUser = repositoryApplication.save(userUpdatedFields);
		return orikaMapperFacade.map(updatedUser, UpdateUserResponse.class);
	}

	public UserDTO getUserById(String id) {

		Optional<User> userFound = repositoryApplication.findById(id);
		if (userFound.isPresent())
			return orikaMapperFacade.map(userFound.get(), UserDTO.class);
		throw new NotFoundException(
				String.format(NOT_FOUND_RESOURCE, USER, id)
		);
	}
	
	@Override
	public ResponseEntity<String> validateToken(String token) {
		ResponseEntity<String> response = null;
		OAuth2AuthenticationAccessToken tokenDB = null;
		tokenDB = tokenRepository.findByTokenId(token);
		response = new ResponseEntity<String>("Valid token", HttpStatus.OK);

		if (StringUtils.isBlank(token))
			throw new UnauthorizedException("Provided token can't be null nor empty");

		if (tokenDB == null)
			throw new UnauthorizedException("Provided token not found");

		if (tokenDB.getOAuth2AccessToken() == null)
			throw new UnauthorizedException(
					"Ivalid access token information, please review token or create a new one.");

		if (tokenDB.getOAuth2AccessToken().isExpired())
			throw new UnauthorizedException("Provided token has expired");

		return response;
	}
		
}
