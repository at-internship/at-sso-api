package com.agilethought.internship.sso.validator.user;

import com.agilethought.internship.sso.exception.BadRequestException;
import com.agilethought.internship.sso.model.User;
import com.agilethought.internship.sso.validator.Validator;

import static com.agilethought.internship.sso.validator.user.ValidationUtils.*;
import static com.agilethought.internship.sso.exception.errorMessages.ErrorMessageCreateUser.*;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.springframework.stereotype.Service;

@Service
public class UserValidator implements Validator<User> {
    private static final Logger logger = LogManager.getLogger();

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
                )
            );
        }
        if (type < 1 || type > 2) {
            throw new BadRequestException(
                String.format(
                    INVALIDATE_INPUT.getErrorMessage(),
                    TYPE.getErrorMessage(),
                    CORRECT_FORMAT_TYPE.getErrorMessage()
                )
            );
        }
    }

    private static void validateFirstName(String firstName) {
        if (!isValidateString(firstName)) {
            throw new BadRequestException(
                String.format(
                    MISSING_REQUIRED_INPUT.getErrorMessage(),
                    FIRST_NAME.getErrorMessage()
                )
            );
        }
    }

    private static void validateLastName(String lastName) {
        if (!isValidateString(lastName)) {
            throw new BadRequestException(
                String.format(
                    MISSING_REQUIRED_INPUT.getErrorMessage(),
                    LAST_NAME.getErrorMessage()
                )
            );
        }
    }

    private static void validateEmail(String email) {
        if (!isValidateString(email)) {
            throw new BadRequestException(
                String.format(
                    MISSING_REQUIRED_INPUT.getErrorMessage(),
                    EMAIL.getErrorMessage()
                )
            );
        }
        if (!isValidEmail(email)) {
            throw new BadRequestException(
                String.format(
                    INVALIDATE_INPUT.getErrorMessage(),
                    EMAIL.getErrorMessage(),
                    CORRECT_FORMAT_EMAIL.getErrorMessage()
                )
            );
        }
    }

    private static void validatePassword(String password) {
        if (!isValidateString(password)) {
            throw new BadRequestException(
                String.format(
                    MISSING_REQUIRED_INPUT.getErrorMessage(),
                    PASSWORD.getErrorMessage()
                )
            );
        }
        if (!isValidatePassword(password)) {
            throw new BadRequestException(
                String.format(
                    INVALIDATE_INPUT.getErrorMessage(),
                    PASSWORD.getErrorMessage(),
                    CORRECT_FORMAT_PASSWORD.getErrorMessage()
                )
            );
        }
    }

    private static void validateStatus(Integer status) {
        if (status == null) {
            throw new BadRequestException(
                String.format(
                    MISSING_REQUIRED_INPUT.getErrorMessage(),
                    STATUS.getErrorMessage()
                )
            );
        }
        if( status < 0 || status > 1) {
            throw new BadRequestException(
                String.format(
                    INVALIDATE_INPUT.getErrorMessage(),
                    STATUS.getErrorMessage(),
                    CORRECT_FORMAT_STATUS.getErrorMessage()
                )
            );
        }
    }
}
