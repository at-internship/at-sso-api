package com.agilethought.internship.sso.services;

import com.agilethought.internship.sso.domain.NewUserRequest;
import com.agilethought.internship.sso.domain.NewUserResponse;
import com.agilethought.internship.sso.domain.UserDTO;
import com.agilethought.internship.sso.model.UserId;
import java.util.List;

public interface ServiceApplication {
	public List<UserDTO> getUsersByEmail(String email) ;
	public NewUserResponse createUser(NewUserRequest userDTO);
	public List<UserDTO> getUsers();
}
