package com.agilethougth.intership.sso.errorhandling;

public class HttpExceptionMessage {

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
}