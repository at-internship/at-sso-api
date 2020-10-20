package com.agilethought.internship.sso.services;

import com.agilethought.internship.sso.domain.UserDTO;
import com.agilethought.internship.sso.model.UserId;
import java.util.List;

public interface ServiceApplication {

	public List<UserDTO> getUsersByEmail(String email) ;
	public UserId createUser(UserDTO userDTO);
	public List<UserDTO> getUsers();
}
