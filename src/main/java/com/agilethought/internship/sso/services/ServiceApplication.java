package com.agilethought.internship.sso.services;

import com.agilethought.internship.sso.dto.*;

import java.util.List;

public interface ServiceApplication {

	List<UserDTO> getUsersByEmail(String email) ;
	
	NewUserResponse createUser(NewUserRequest request);
	
	List<UserDTO> getAllUsers();
	
	UpdateUserResponse updateUserById(UpdateUserRequest request, String id);
	
	UserDTO getUserById(String id);
}
