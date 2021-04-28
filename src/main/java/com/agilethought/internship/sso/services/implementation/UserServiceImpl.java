package com.agilethought.internship.sso.services.implementation;

import static com.agilethought.internship.sso.exception.ErrorMessage.NOT_FOUND_RESOURCE;
import static com.agilethought.internship.sso.exception.ErrorMessage.USER;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agilethought.internship.sso.exception.NotFoundException;
import com.agilethought.internship.sso.model.User;
import com.agilethought.internship.sso.repository.RepositoryApplication;
import com.agilethought.internship.sso.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private RepositoryApplication RepositoryApplication;

	@Override
	public void deleteUserById(String id) {

		Optional<User> userFoundById = RepositoryApplication.findById(id);
		if (!userFoundById.isPresent())
			throw new NotFoundException(String.format(NOT_FOUND_RESOURCE, USER, id));
		RepositoryApplication.deleteById(id);

	}

}
