package com.agilethought.internship.sso.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@Setter
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private int status;
	private HttpStatus error;
	private String message;
	private String path;
	
	public BadRequestException(String message, String path,HttpStatus r){
		setStatus(r.value());
		setError(HttpStatus.BAD_REQUEST);
		setMessage(message);
		setPath(path);
	}
	
}
