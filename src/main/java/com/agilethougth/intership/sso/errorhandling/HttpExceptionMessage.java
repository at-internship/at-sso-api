package com.agilethougth.intership.sso.errorhandling;

public class HttpExceptionMessage {

	public static final String BadRequestJSON = "Malformed JSON request.";

	public static final String BadRequestEmptyNullName = "Name is required.";
	public static final String BadRequestEmptyNullFirstName = "First Name is required.";
	public static final String BadRequestEmptyNullLastName = "Last Name is required.";
	public static final String BadRequestEmptyNullPassword = "Password is required.";
	public static final String BadRequestInvalidStatus = "Invalid status. Field should be 0 or 1.";
	public static final String BadRequestEmptyNullStatus = "Status is required.";
	public static final String BadRequestEmptyNullMail = "Mail is required.";
	public static final String BadRequestFormatMail = "Format required: {Name}@{domain].{com,es}.";
	public static final String BadRequestMailAlreadyExists = "Mail already exists.";
}