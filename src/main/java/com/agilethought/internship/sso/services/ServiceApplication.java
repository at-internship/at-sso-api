package com.agilethought.internship.sso.services;

import com.agilethought.internship.sso.model.User;
import com.agilethought.internship.sso.model.UserId;

public interface ServiceApplication {

	public UserId createUser(User user);
	public java.util.List<User> getUsers();

}
