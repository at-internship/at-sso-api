package com.agilethought.internship.sso.services;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.agilethought.internship.sso.dto.NewUserRequest;
import com.agilethought.internship.sso.dto.NewUserResponse;
import com.agilethought.internship.sso.dto.UpdateUserRequest;
import com.agilethought.internship.sso.dto.UpdateUserResponse;
import com.agilethought.internship.sso.dto.UserDTO;

public interface ServiceApplication {

	List<UserDTO> getUsersByEmail(String email) ;
	
	NewUserResponse createUser(NewUserRequest request);
	
	List<UserDTO> getAllUsers();
	
	void deleteUserById(String id);
	
	UpdateUserResponse updateUserById(UpdateUserRequest request, String id);

	UserDTO getUserById(String id);
	
	ResponseEntity<String> validateToken(String token);
}
