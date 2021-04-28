package com.agilethought.internship.sso.controller;

import com.agilethought.internship.sso.dto.NewUserRequest;
import com.agilethought.internship.sso.dto.NewUserResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.agilethought.internship.sso.dto.UserDTO;
import com.agilethought.internship.sso.services.ServiceApplication;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
@Slf4j
@Api(value = "Software Cost Estimation", tags = "SCE")
public class ControllerApplication {

	@Autowired
	ServiceApplication serviceApplication;

	@PostMapping(value = "/users", produces = "application/json")
	@ResponseStatus(value = HttpStatus.CREATED)
	@ApiOperation(value = "Create New User in the application")
	@ApiResponses(value = {
			@ApiResponse(code = 201, message = "Resource created successfully"),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 403, message = "Access prohibited"),
			@ApiResponse(code = 404, message = "Not Found"),
	})
	public NewUserResponse createUser(@RequestBody NewUserRequest request) {
		return serviceApplication.createUser(request);
	}

	@GetMapping(value = "/users", produces = "application/json")
	@ResponseStatus(value = HttpStatus.OK)
	@ApiOperation(value = "Get all users from the database of the application")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Search in the database without parameters"),
			@ApiResponse(code = 201, message = "Resource created successfully"),
			@ApiResponse(code = 401, message = "Unauthorized"),
			@ApiResponse(code = 403, message = "Access prohibited"),
			@ApiResponse(code = 404, message = "Not Found"),
	})
	public List<UserDTO> getAllUsers() {
		log.info("ControllerApplication.getAllUsers - Calling get operation");
		return serviceApplication.getAllUsers();
	}
}