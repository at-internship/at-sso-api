package com.agilethought.internship.sso.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import com.agilethought.internship.sso.domain.UserDTO;
import com.agilethought.internship.sso.model.UserId;
import com.agilethought.internship.sso.services.ServiceApplication;
import java.util.List;

@RestController
@Slf4j
public class ControllerAplication {

	@Autowired
	ServiceApplication serviceApplication;

	@PostMapping(value = "/api/v1/user", produces = "application/json")
	@ResponseStatus(value = HttpStatus.CREATED)
	public UserId createUser(@RequestBody UserDTO userDTO) {
		return serviceApplication.createUser(userDTO);
	}// End createUser

	@GetMapping(value = "/api/v1/user", produces = "application/json")
	@ResponseStatus(value = HttpStatus.OK)
	public List<UserDTO> getAllUsers() {
		log.info("ControllerAplication.getAllUsers - Calling get operation");
		return serviceApplication.getUsers();
	}
}