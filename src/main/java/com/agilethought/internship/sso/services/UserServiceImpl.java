package com.agilethought.internship.sso.services;

import static com.agilethought.internship.sso.exception.ErrorMessage.INVALID_CREDENTIALS;
import static com.agilethought.internship.sso.exception.ErrorMessage.UNAVAILABLE_ENTITY;
import static com.agilethought.internship.sso.exception.ErrorMessage.USER;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agilethought.internship.sso.model.User;
import com.agilethought.internship.sso.repository.UserRepository;
import com.agilethought.internship.sso.dto.LoginRequest;
import com.agilethought.internship.sso.dto.LoginResponse;
import com.agilethought.internship.sso.validator.Validator;
import com.agilethought.internship.sso.exception.UnauthorizedException;

import lombok.extern.slf4j.Slf4j;

import ma.glasnost.orika.MapperFacade;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private MapperFacade orikaMapperFacade;

	@Autowired
	private Validator<LoginRequest> loginValidator;

	@Override
	public LoginResponse loginUser(LoginRequest loginRequest) {

		loginValidator.validate(loginRequest);
		List<User> users = userRepository.findUserWithCredentials(
				loginRequest.getEmail(),
				loginRequest.getPassword()
		);
		if (!users.isEmpty()) {
			log.info("UserServiceImpl.loginUser: got user " + users.get(0) + " from Database");
			User user = users.get(0);
			if (user.getStatus() == 0)
				throw new UnauthorizedException(
						String.format(UNAVAILABLE_ENTITY, USER)
				);
			return orikaMapperFacade.map(user, LoginResponse.class);
		}
		throw new UnauthorizedException(INVALID_CREDENTIALS);
	}
}