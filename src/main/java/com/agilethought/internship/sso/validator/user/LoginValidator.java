package com.agilethought.internship.sso.validator.user;

import static com.agilethought.internship.sso.exception.ErrorMessage.*;
import static com.agilethought.internship.sso.validator.user.ValidationUtils.isValidPassword;
import static com.agilethought.internship.sso.validator.user.ValidationUtils.isValidString;

import java.util.ArrayList;
import java.util.List;

import com.agilethought.internship.sso.validator.Validator;
import org.springframework.stereotype.Service;

import com.agilethought.internship.sso.exception.BadRequestExceptionNew;
import com.agilethought.internship.sso.exception.GlobalExceptionBody.ErrorDetails;
import com.agilethought.internship.sso.dto.LoginRequest;
import com.agilethought.internship.sso.exception.UnauthorizedException;
import org.apache.commons.collections4.CollectionUtils;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class LoginValidator implements Validator<LoginRequest> {
	
	@Override
	public void validate(LoginRequest loginRequest) {
		List<ErrorDetails> errorDetails = new ArrayList<ErrorDetails>();
		validateEmail(loginRequest.getEmail(), errorDetails);
		validatePassword(loginRequest.getPassword(), errorDetails);
		if (CollectionUtils.isNotEmpty(errorDetails))
			throw new BadRequestExceptionNew(VALIDATION_ERROR, errorDetails);
	}

	private void validateEmail(String email, List<ErrorDetails> errorDetails) {

		if (!isValidString(email)) {
			ErrorDetails error = new ErrorDetails();
			error.setErrorMessage(String.format(MISSING_REQUIRED_INPUT, EMAIL));
			error.setFieldName(EMAIL);
			errorDetails.add(error);
			return;
		}
		if (!ValidationUtils.isValidEmail(email))
			throw new UnauthorizedException(INVALID_CREDENTIALS);

	}

	private void validatePassword(String password, List<ErrorDetails> errorDetails) {

		if (!isValidString(password)) {
			ErrorDetails error = new ErrorDetails();
			error.setErrorMessage(String.format(MISSING_REQUIRED_INPUT, PASSWORD));
			error.setFieldName(PASSWORD);
			errorDetails.add(error);
			return;
		}
		if (!isValidPassword(password))
			throw new UnauthorizedException(INVALID_CREDENTIALS);
	}


}