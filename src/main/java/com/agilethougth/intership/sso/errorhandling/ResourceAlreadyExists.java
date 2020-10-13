package com.agilethougth.intership.sso.errorhandling;

public class ResourceAlreadyExists extends RuntimeException {

    public ResourceAlreadyExists(String message) {
        super(message);
    }
}