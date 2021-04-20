package com.agilethought.internship.sso.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.http.HttpStatus;
import com.agilethought.internship.sso.domain.UserDTO;
import com.agilethought.internship.sso.exception.BadRequestException;
import com.agilethought.internship.sso.exception.errorMessages.HttpExceptionMessage;
import com.agilethought.internship.sso.exception.errorMessages.PathErrorMessage;

public class BusinessValidations {

	private static void emptyNullFirstName(String firstname) {
		if (firstname == null || firstname.equals("")) {
			throw new BadRequestException(HttpExceptionMessage.BAD_REQUEST_EMPTY_NULL_FIRST_NAME,
					PathErrorMessage.PATH_API, HttpStatus.BAD_REQUEST);
		}

	}

	private static void emptyNullLastName(String lastname) {
		if (lastname == null || lastname.equals("")) {
			throw new BadRequestException(HttpExceptionMessage.BAD_REQUEST_EMPTY_NULL_LAST_NAME,
					PathErrorMessage.PATH_API, HttpStatus.BAD_REQUEST);
		}
	}

	private static void emptyNullWrongEmail(String email) {
		if (email == null || email.equals("")) {
			throw new BadRequestException(HttpExceptionMessage.BAD_REQUEST_EMPTY_NULL_MAIL, PathErrorMessage.PATH_API,
					HttpStatus.BAD_REQUEST);
		}
		else {
			Pattern pattern = Pattern.compile(
					"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
			Matcher mather = pattern.matcher(email);

			if (mather.find() == false) {
				throw new BadRequestException(HttpExceptionMessage.BAD_REQUEST_FORMAT_MAIL, PathErrorMessage.PATH_API,
						HttpStatus.BAD_REQUEST);
			}
		}
	}

	private static void emptyNullPassword(String password) {
		if (password == null || password.equals(""))
			throw new BadRequestException(HttpExceptionMessage.BAD_REQUEST_EMPTY_NULL_PASSWORD,
					PathErrorMessage.PATH_API, HttpStatus.BAD_REQUEST);
	}

	private static void filterCharacters(String password) {
        if (password.contains(" ") || password.contains("\n"))
        throw new BadRequestException(HttpExceptionMessage.FILTER_CHARACTERS, PathErrorMessage.PATH_API, HttpStatus.BAD_REQUEST);
    }


	public static void validate(UserDTO userDTO) {

		emptyNullFirstName(userDTO.getFirstName());
		emptyNullLastName(userDTO.getLastName());
		emptyNullWrongEmail(userDTO.getEmail());
		emptyNullPassword(userDTO.getPassword());
		filterCharacters(userDTO.getPassword());

	}

}// End class
