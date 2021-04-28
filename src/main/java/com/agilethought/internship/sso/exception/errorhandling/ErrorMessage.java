package com.agilethought.internship.sso.exception.errorhandling;

public class ErrorMessage {

	public static final String MISSING_REQUIRED_INPUT = "Required field %s is missing.";
	public static final String ID = "Id";

	public static final String INVALID_INPUT = "Invalid input on field %s. Correct format is: %s";
	public static final String CORRECT_FORMAT_NUMERIC = "A number with or " +
			"without a decimal point.";

	public static final String EMAIL = "Email";
	public static final String ALREADY_EXISTING_EMAIL = "Email %s is already in use, " +
			"try with another one or login.";

	public static final String PASSWORD = "Password";

	public static final String NOT_FOUND_RESOURCE = "%s was not found with the given id: %s";

	public static final String HISTORY = "History";
	public static final String USER = "User";

	public static final String INVALID_CREDENTIALS = "Invalid login credentials.";
	public static final String UNAVAILABLE_ENTITY = "This %s is currently unavailable.";
	public static final String VALIDATION_ERROR = "One or more fields are invalid";

	public static final String BAD_REQUEST_JSON = "Malformed JSON request.";

	public static final String BAD_REQUEST_EMPTY_NULL_NAME = "Name is required.";
	public static final String BAD_REQUEST_EMPTY_NULL_FIRST_NAME = "First Name is required.";
	public static final String BAD_REQUEST_EMPTY_NULL_LAST_NAME = "Last Name is required.";
	public static final String BAD_REQUEST_EMPTY_NULL_PASSWORD = "Password is required.";
	public static final String BAD_REQUEST_INVALID_STATUS = "Invalid status. Field should be 0 or 1.";
	public static final String BAD_REQUEST_EMPTY_NULL_STATUS = "Status is required.";
	public static final String BAD_REQUEST_EMPTY_NULL_MAIL = "Mail is required.";
	public static final String BAD_REQUEST_FORMAT_MAIL = "Format required: {Name}@{domain].{com,es}.";
	public static final String BAD_REQUEST_MAIL_ALREADY_EXISTS = "Mail already exists.";
	public static final String FILTER_CHARACTERS = "Characters 'Space' and 'Line Break' are invalids";

	private ErrorMessage() {
	}
}