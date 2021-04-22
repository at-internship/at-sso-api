package com.agilethought.internship.sso.services;

import com.agilethought.internship.sso.validator.user.UserValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.agilethought.internship.sso.domain.UserDTO;
import com.agilethought.internship.sso.exception.BadRequestException;
import com.agilethought.internship.sso.mapper.UserTransformer;
import com.agilethought.internship.sso.model.User;
import com.agilethought.internship.sso.model.UserId;
import com.agilethought.internship.sso.repository.RepositoryApplication;
import static com.agilethought.internship.sso.exception.errorMessages.ErrorMessageCreateUser.ALREADY_EXISTING_EMAIL;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import java.util.List;

@Service
public class ServiceApplicationimpl implements ServiceApplication {
	private static final Logger logger = LogManager.getLogger();

	@Autowired
	private UserTransformer userTransformer;

	@Autowired
	private RepositoryApplication repositoryApplication;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private UserValidator userValidator;
	

	@Override
	public UserId createUser(UserDTO userDTO) {
		UserId userId = new UserId();
		User user = userTransformer.transformer(userDTO);
		userValidator.validate(user);
		if (repositoryApplication.existsByEmail(user.getEmail())) {
			throw new BadRequestException(
				String.format(
					ALREADY_EXISTING_EMAIL.getErrorMessage(),
					user.getEmail()
				)
			);
		}
		setLetterCases(user);
		logger.info("ServiceApplicationimpl.createUser - users transformed: {}", user);
		String userIdDb = repositoryApplication.save(user).getId();
		logger.info("ServiceApplicationimpl.createUser- User saved  successfully with id: {}", user.getId());
		userId.setId(userIdDb);
		logger.info("ServiceApplicationimpl.createUser- User created successfully on mongoDB: {}", userId);
		return userId;
	}

	@Override
	public List<UserDTO> getUsers() {
		logger.info("ServiceApplicationimpl.getUsers - Before getting all the users");
		List<User> response = repositoryApplication.findAll();
		logger.info("ServiceApplicationimpl.getUsers -  Consulted successfully on mongoDB: {}", response);
		return userTransformer.listTransformer(response);
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
