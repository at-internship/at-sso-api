package com.agilethought.internship.sso.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

import com.agilethought.internship.sso.model.User;
import com.agilethought.internship.sso.model.UserId;
import com.agilethought.internship.sso.repository.RepositoryApplication;

import antlr.collections.List;

@Service
@Slf4j
public class ServiceApplicationimpl implements ServiceApplication {

	@Autowired
	private RepositoryApplication repositoryApplication;
	
	@Override
	public UserId createUser(User user) {
		String userIdDb = repositoryApplication.save(user).getId();
		log.info("Created sucessfully on mongoDB");
		UserId userId = new UserId();
		userId.setId(userIdDb);
		return userId;
	}
	
	@Override
	public java.util.List<User> getUsers() {
		java.util.List<User> response=repositoryApplication.findAll();
		log.info("Consulted sucessfully on mongoDB");
		return response;
	}
}
