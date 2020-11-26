package com.agilethougth.intership.sso.errorhandling;

public class HttpExceptionMessage {
	
	public static final String BadRequestJSON = "Malformed JSON request.";

	public static final String BadRequestEmptyName = "Name is required.";
	public static final String BadRequestEmptyFirstName= "First Name is required.";
	public static final String BadRequestEmptyPassword= "Password is required.";
	public static final String FilterCharacters= "Characters 'Space' and 'Line Break' are invalids";
	public static final String BadRequestInvalidStatus= "Invalid status. Field should be 0 or 1.";
	public static final String BadRequestEmptyStatus= "Status is required.";
	public static final String BadRequestEmptyMail= "Mail is required.";
	public static final String BadRequestFormatMail= "Format required: {Name}@{domain].{com,es}.";
	public static final String BadRequestMailAlreadyExists= "Mail already exists.";
}