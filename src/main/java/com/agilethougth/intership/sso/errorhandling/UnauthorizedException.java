package com.agilethougth.intership.sso.errorhandling;

public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException(String message) {
        super(message);
    }

}