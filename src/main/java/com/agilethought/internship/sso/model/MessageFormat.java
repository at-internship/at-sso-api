package com.agilethought.internship.sso.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MessageFormat {
	
	private String timestamp;
	private int status;
	private String error;
	private String message;
	private String path;

}