package com.agilethought.internship.sso.services;

import com.agilethought.internship.sso.model.User;
import com.agilethought.internship.sso.model.UserId;

import java.util.List;

public interface ServiceApplication {

	public UserId createUser(User user);
	public List<User> getUsers();
	public List<User> getUsersByEmail(String email) ;

}
