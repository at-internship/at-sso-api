package com.agilethought.internship.sso.services;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.agilethought.internship.sso.exception.BadRequestException;
import com.agilethougth.intership.sso.errorhandling.HttpExceptionMessage;
import com.agilethougth.intership.sso.errorhandling.PathErrorMessage;

public class BusinessValidations {

	@Autowired
	ServiceApplication serviceApplication;

	public static boolean emptyNullFirstName(String firstname) {
		boolean response = true;
		if (firstname != null && !firstname.equals(""))
			response = false;
		else
			throw new BadRequestException(HttpExceptionMessage.BadRequestEmptyNullFirstName, PathErrorMessage.pathApi,
					HttpStatus.BAD_REQUEST);
		return response;
	}

	public static boolean emptyNullLastName(String lastname) {
		boolean response = true;
		if (lastname != null && !lastname.equals(""))
			response = false;
		else
			throw new BadRequestException(HttpExceptionMessage.BadRequestEmptyNullLastName, PathErrorMessage.pathApi,
					HttpStatus.BAD_REQUEST);
		return response;
	}

	public static boolean emptyNullWrongEmail(String email) {
		boolean response = true;
		if (email != null && !email.equals("")) {
			Pattern pattern = Pattern.compile(
					"^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$");
			Matcher mather = pattern.matcher(email);

			if (mather.find() == true)
				response = false;
			else
				throw new BadRequestException(HttpExceptionMessage.BadRequestFormatMail, PathErrorMessage.pathApi,
						HttpStatus.BAD_REQUEST);
		} else {
			response = true;
			throw new BadRequestException(HttpExceptionMessage.BadRequestEmptyNullMail, PathErrorMessage.pathApi,
					HttpStatus.BAD_REQUEST);
		}
		return response;
	}

	public static boolean emptyNullPassword(String password) {
		boolean response = true;
		if (password != null && !password.equals(""))
			response = false;
		else
			throw new BadRequestException(HttpExceptionMessage.BadRequestEmptyNullPassword, PathErrorMessage.pathApi,
					HttpStatus.BAD_REQUEST);
		return response;
	}

}// End class
