package com.agilethought.internship.sso.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import com.agilethought.internship.sso.model.User;
import com.agilethought.internship.sso.model.UserId;
import com.agilethought.internship.sso.repository.RepositoryApplication;

import java.util.List;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
@Slf4j
public class ServiceApplicationimpl implements ServiceApplication {

	@Autowired
	private RepositoryApplication repositoryApplication;

	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Override
	public UserId createUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		String userIdDb = repositoryApplication.save(user).getId();
		log.info("Created sucessfully on mongoDB");
		UserId userId = new UserId();
		userId.setId(userIdDb);
		return userId;
	}
	
	@Override
	public List<User> getUsers() {
		List<User> response=repositoryApplication.findAll();
		log.info("Consulted sucessfully on mongoDB");
		return response;
	}
}
