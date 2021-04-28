package com.agilethought.internship.sso.services;

import com.agilethought.internship.sso.model.User;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PopulateFields {
	public static void setLetterCases(User request){
		request.setFirstName(request.getFirstName().toUpperCase());
		request.setLastName(request.getLastName().toUpperCase());
		request.setEmail(request.getEmail().toLowerCase());
	}

	private PopulateFields() {
	}
}
