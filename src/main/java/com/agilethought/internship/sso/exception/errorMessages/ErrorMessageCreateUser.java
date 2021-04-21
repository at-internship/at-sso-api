package com.agilethought.internship.sso.exception.errorMessages;

public enum ErrorMessageCreateUser {
    MISSING_REQUIRED_INPUT("Required field %s is missing."),
    INVALIDATE_INPUT("Invalid input on field %s. Correct format is: %s"),
    CORRECT_FORMAT_NUMERIC("A number with or without a decimal point."),
    ALREADY_EXISTING_EMAIL("The email %s is already in use, try with another one or login."),
    CORRECT_FORMAT_TYPE("Number 1 for admin or number 2 for normal user"),
    CORRECT_FORMAT_EMAIL("an_accepted-email.example@domain.com.mx"),
    CORRECT_FORMAT_PASSWORD("10 characters minimum with " +
            "at least one lowercase letter, one uppercase letter, and one number."),
    CORRECT_FORMAT_STATUS("Number 0 for unavailable or " +
            "number 1 for available"),
    NOT_FOUND_RESOURCE("%s was not found with the given id: %s"),
    INVALIDATE_CREDENTIALS("Invalid login credentials."),
    UNAVAILABLE_ENTITY("This %s is currently unavailable."),
    FIRST_NAME("First name"),
    LAST_NAME("Last name"),
    STATUS("Status"),
    TYPE("Type"),
    USER("User"),
    EMAIL("Email"),
    PASSWORD("Password");

    private final String errorMessage;

    ErrorMessageCreateUser(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getErrorMessage() {
        return this.errorMessage;
    }
}
