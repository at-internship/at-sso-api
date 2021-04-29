package com.agilethought.internship.sso.services;

import com.agilethought.internship.sso.dto.*;

import java.util.List;

public interface ServiceApplication {

	List<UserDTO> getUsersByEmail(String email) ;
	
	NewUserResponse createUser(NewUserRequest request);
	
	List<UserDTO> getAllUsers();
	
	void deleteUserById(String id);
	
	UpdateUserResponse updateUserById(UpdateUserRequest request, String id);
	LoginResponse loginUser(LoginRequest loginRequest);
}