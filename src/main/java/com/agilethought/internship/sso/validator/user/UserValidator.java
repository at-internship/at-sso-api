package com.agilethought.internship.sso.validator.user;

import com.agilethought.internship.sso.exception.BadRequestException;
import com.agilethought.internship.sso.model.User;
import com.agilethought.internship.sso.validator.Validator;

import static com.agilethought.internship.sso.validator.ValidationUtils.*;
import static com.agilethought.internship.sso.exception.errorMessages.ErrorMessageCreateUser.*;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserValidator implements Validator<User> {
    private static final Logger logger = LogManager.getLogger();
    private static final String PATH_API = "/api/v1/users/id";

    @Override
    public void validate(User user) {
        validateType(user.getType());
        validateFirstName(user.getFirstName());
        validateLastName(user.getLastName());
        validateEmail(user.getEmail());
        validatePassword(user.getPassword());
        validateStatus(user.getStatus());
    }

    private static void validateType(Integer type) {
        if (type == null) {
            throw new BadRequestException(
                String.format(
                    MISSING_REQUIRED_INPUT.getErrorMessage(),
                    TYPE.getErrorMessage()
                ),PATH_API, HttpStatus.BAD_REQUEST
            );
        }
        if (type < 1 || type > 2) {
            throw new BadRequestException(
                String.format(
                    INVALIDATE_INPUT.getErrorMessage(),
                    TYPE.getErrorMessage(),
                    CORRECT_FORMAT_TYPE.getErrorMessage()
                ),PATH_API, HttpStatus.BAD_REQUEST
            );
        }
    }

    private static void validateFirstName(String firstName) {
        if (!isValidString(firstName)) {
            throw new BadRequestException(
                String.format(
                    MISSING_REQUIRED_INPUT.getErrorMessage(),
                    FIRST_NAME.getErrorMessage()
                ),PATH_API, HttpStatus.BAD_REQUEST
            );
        }
    }

    private static void validateLastName(String lastName) {
        if (!isValidString(lastName)) {
            throw new BadRequestException(
                String.format(
                    MISSING_REQUIRED_INPUT.getErrorMessage(),
                    LAST_NAME.getErrorMessage()
                ),PATH_API, HttpStatus.BAD_REQUEST
            );
        }
    }

    private static void validateEmail(String email) {
        if (!isValidString(email)) {
            throw new BadRequestException(
                String.format(
                    MISSING_REQUIRED_INPUT.getErrorMessage(),
                    EMAIL.getErrorMessage()
                ),PATH_API, HttpStatus.BAD_REQUEST
            );
        }
        if (!isValidEmail(email)) {
            throw new BadRequestException(
                String.format(
                    INVALIDATE_INPUT.getErrorMessage(),
                    EMAIL.getErrorMessage(),
                    CORRECT_FORMAT_EMAIL.getErrorMessage()
                ),PATH_API, HttpStatus.BAD_REQUEST
            );
        }
    }

    private static void validatePassword(String password) {
        if (!isValidString(password)) {
            throw new BadRequestException(
                String.format(
                    MISSING_REQUIRED_INPUT.getErrorMessage(),
                    PASSWORD.getErrorMessage()
                ),PATH_API, HttpStatus.BAD_REQUEST
            );
        }
        if (!isValidPassword(password)) {
            throw new BadRequestException(
                String.format(
                    INVALIDATE_INPUT.getErrorMessage(),
                    PASSWORD.getErrorMessage(),
                    CORRECT_FORMAT_PASSWORD.getErrorMessage()
                ),PATH_API, HttpStatus.BAD_REQUEST
            );
        }
    }

    private static void validateStatus(Integer status) {
        if (status == null) {
            throw new BadRequestException(
                String.format(
                    MISSING_REQUIRED_INPUT.getErrorMessage(),
                    STATUS.getErrorMessage()
                ),PATH_API, HttpStatus.BAD_REQUEST
            );
        }
        if( status < 0 || status > 1) {
            throw new BadRequestException(
                String.format(
                    INVALIDATE_INPUT.getErrorMessage(),
                    STATUS.getErrorMessage(),
                    CORRECT_FORMAT_STATUS.getErrorMessage()
                ),PATH_API, HttpStatus.BAD_REQUEST
            );
        }
    }
}
