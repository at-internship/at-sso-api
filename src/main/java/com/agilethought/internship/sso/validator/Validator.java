package com.agilethought.internship.sso.validator;

public interface Validator<E> {
    void validate(E object);
}
