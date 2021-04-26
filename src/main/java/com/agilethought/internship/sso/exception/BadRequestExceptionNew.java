package com.agilethought.internship.sso.exception;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.agilethought.internship.sso.exception.GlobalExceptionBody.ErrorDetails;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestExceptionNew extends RuntimeException{
	private List<ErrorDetails> errorDetails;
	
	public BadRequestExceptionNew(String message) {
        super(message);
    }
	
	 public BadRequestExceptionNew(String message, List<ErrorDetails> details) {
	    	super(message);
	    	errorDetails = details;
	 }
}
