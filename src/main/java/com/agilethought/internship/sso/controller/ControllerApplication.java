package com.agilethought.internship.sso.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.agilethought.internship.sso.dto.LoginRequest;
import com.agilethought.internship.sso.dto.LoginResponse;
import com.agilethought.internship.sso.dto.NewUserRequest;
import com.agilethought.internship.sso.dto.NewUserResponse;
import com.agilethought.internship.sso.dto.UpdateUserRequest;
import com.agilethought.internship.sso.dto.UpdateUserResponse;
import com.agilethought.internship.sso.dto.UserDTO;
import com.agilethought.internship.sso.services.ServiceApplication;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

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
	@ApiResponses(value = { @ApiResponse(code = 201, message = "Resource created successfully"),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Access prohibited"),
			@ApiResponse(code = 404, message = "Not Found"), })
	public NewUserResponse createUser(@RequestBody NewUserRequest request) {
		return serviceApplication.createUser(request);
	}

	@GetMapping(value = "/users", produces = "application/json")
	@ResponseStatus(value = HttpStatus.OK)
	@ApiOperation(value = "Get all users from the database of the application")
	@ApiResponses(value = { @ApiResponse(code = 200, message = "Search in the database without parameters"),
			@ApiResponse(code = 201, message = "Resource created successfully"),
			@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Access prohibited"),
			@ApiResponse(code = 404, message = "Not Found"), })
	public List<UserDTO> getAllUsers() {
		log.info("ControllerApplication.getAllUsers - Calling get operation");
		return serviceApplication.getAllUsers();
	}

	@DeleteMapping(value = "/users/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@ApiOperation(value = "Delete a User")
	@ApiResponses(value = { @ApiResponse(code = 204, message = "No Content"),
			@ApiResponse(code = 404, message = "Not Found"), })
	public void deleteUserById(@PathVariable String id) {

		serviceApplication.deleteUserById(id);
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

		return serviceApplication.updateUserById(request, id);

	}
	
	 @GetMapping("/users/{id}")
	    @ResponseStatus(HttpStatus.OK)
	    @ApiOperation(value = "Validation the session of the user")
	    @ApiResponses(value = {
	            @ApiResponse(code = 200, message = "Search in the database without parameters"),
	            @ApiResponse(code = 401, message = "Unauthorized"),
	            @ApiResponse(code = 403, message = "Access prohibited"),
	            @ApiResponse(code = 404, message = "Not Found"),
	    })
	    public UserDTO getUserById(@PathVariable String id) {

	        return serviceApplication.getUserById(id);

	    }
	 
	 @GetMapping("/tokens/{id}")
		@ResponseStatus(HttpStatus.OK)
		@ApiOperation(value = "Validates provided access token")
		@ApiResponses(value = { @ApiResponse(code = 200, message = "Found valid token"),
				@ApiResponse(code = 401, message = "Unauthorized"), @ApiResponse(code = 403, message = "Access prohibited")})
		public ResponseEntity<String> validateToken(@PathVariable String id) {
			return serviceApplication.validateToken(id);
		}
	 
}