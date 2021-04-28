package com.agilethought.internship.sso.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestExceptionNew extends RuntimeException{
    public BadRequestExceptionNew(String message) {
        super(message);
    }
}