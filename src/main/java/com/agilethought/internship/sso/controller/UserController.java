package com.agilethought.internship.sso.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import com.agilethought.internship.sso.dto.LoginRequest;
import com.agilethought.internship.sso.dto.LoginResponse;
import com.agilethought.internship.sso.services.UserService;

@RestController
@RequestMapping(value = "/api/v1")
@Api(value="Software Cost Estimation", tags = "SCE")
public class UserController {
    @Autowired
    private UserService userService;

    @ResponseStatus(HttpStatus.OK)
    @PostMapping(value = "/login")
    @ApiOperation(value = "Let the user log into the application")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Search in the database without parameters"),
            @ApiResponse(code = 401, message = "Unauthorized"),
            @ApiResponse(code = 403, message = "Access prohibited"),
    })
    public LoginResponse loginUser(@RequestBody LoginRequest loginRequest) {
    	return userService.loginUser(loginRequest);
    }
}