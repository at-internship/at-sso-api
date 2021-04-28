package com.agilethought.internship.sso.services;

import java.util.List;

import com.agilethought.internship.sso.dto.LoginRequest;
import com.agilethought.internship.sso.dto.LoginResponse;

public interface UserService {

	LoginResponse loginUser(LoginRequest loginRequest);


} 