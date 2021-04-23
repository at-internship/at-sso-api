package com.agilethought.internship.sso.controller;

import com.agilethought.internship.sso.domain.NewUserRequest;
import com.agilethought.internship.sso.domain.NewUserResponse;
import com.agilethought.internship.sso.domain.UpdateUserRequest;
import com.agilethought.internship.sso.domain.UpdateUserResponse;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.agilethought.internship.sso.domain.UserDTO;
import com.agilethought.internship.sso.model.UserId;
import com.agilethought.internship.sso.services.ServiceApplication;
import java.util.List;

@RestController
@Slf4j
public class ControllerApplication {

	@Autowired
	ServiceApplication serviceApplication;

	@PostMapping(value = "/api/v1/user", produces = "application/json")
	@ResponseStatus(value = HttpStatus.CREATED)
	public NewUserResponse createUser(@RequestBody NewUserRequest request) {
		return serviceApplication.createUser(request);
	}

	@GetMapping(value = "/api/v1/user", produces = "application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public List<UserDTO> getAllUsers() {
		log.info("ControllerApplication.getAllUsers - Calling get operation");
		return serviceApplication.getUsers();
	}

	@ResponseStatus(HttpStatus.OK)
	@PutMapping(value = "/users/{id}", consumes = "application/json", produces = "application/json")
	@ApiOperation(value = "Update a User in the application")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Resource update successfully"),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 403, message = "Access prohibited"),
			@ApiResponse(code = 404, message = "Not Found"),
	})
	public UpdateUserResponse putUser(@RequestBody UpdateUserRequest request, @PathVariable String id) {

		return serviceApplication.updateUserById(request,id);

	}

}