package com.agilethought.internship.sso.services;

import com.agilethought.internship.sso.dto.NewUserRequest;
import com.agilethought.internship.sso.dto.NewUserResponse;
import com.agilethought.internship.sso.dto.UserDTO;
import java.util.List;

public interface ServiceApplication {

	List<UserDTO> getUsersByEmail(String email) ;
	NewUserResponse createUser(NewUserRequest request);
	List<UserDTO> getAllUsers();
}
