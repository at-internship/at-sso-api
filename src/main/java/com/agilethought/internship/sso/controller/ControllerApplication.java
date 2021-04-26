package com.agilethought.internship.sso.controller;

import com.agilethought.internship.sso.domain.NewUserRequest;
import com.agilethought.internship.sso.domain.NewUserResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import com.agilethought.internship.sso.domain.UserDTO;
import com.agilethought.internship.sso.model.UserId;
import com.agilethought.internship.sso.services.ServiceApplication;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
@Slf4j
public class ControllerApplication {

	@Autowired
	ServiceApplication serviceApplication;

	@PostMapping(value = "/users", produces = "application/json")
	@ResponseStatus(value = HttpStatus.CREATED)
	public NewUserResponse createUser(@RequestBody NewUserRequest request) {
		return serviceApplication.createUser(request);
	}

	@GetMapping(value = "/users", produces = "application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public List<UserDTO> getAllUsers() {
		log.info("ControllerApplication.getAllUsers - Calling get operation");
		return serviceApplication.getUsers();
	}
}