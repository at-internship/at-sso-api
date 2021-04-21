package com.agilethought.internship.sso.validator.user;

import com.agilethought.internship.sso.exception.BadRequestException;
import com.agilethought.internship.sso.exception.errorMessages.ErrorMessageCreateUser;
import com.agilethought.internship.sso.model.User;
import com.agilethought.internship.sso.validator.Validator;
import static com.agilethought.internship.sso.validator.user.ValidationUtils.*;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class UserValidator implements Validator<User> {
    private static final Logger logger = LogManager.getLogger();

    @Override
    public void validate(User user) {
        logger.info("Validating all the user data");
        validateType(user.getType());
        validateFirstName(user.getFirstName());
        validateLastName(user.getLastName());
        validateEmail(user.getEmail());
        validatePassword(user.getPassword());
        validateStatus(user.getStatus());
    }

    private static void validateType(Integer type) {
        logger.info("validating the type field");
        if (type == null) {
            throw new BadRequestException(
                String.format(
                    ErrorMessageCreateUser.INVALIDATE_INPUT.getErrorMessage(),
                    ErrorMessageCreateUser.TYPE.getErrorMessage()
                )
            );
        }
        if (type < 1 || type > 2) {
            throw new BadRequestException(
                String.format(
                    ErrorMessageCreateUser.INVALIDATE_INPUT.getErrorMessage(),
                    ErrorMessageCreateUser.CORRECT_FORMAT_TYPE.getErrorMessage()
                )
            );
        }
    }

    private static void validateFirstName(String firstName) {
        logger.info("validating the firstName field");
        if (!isValidateString(firstName)) {
            throw new BadRequestException(
                String.format(
                    ErrorMessageCreateUser.MISSING_REQUIRED_INPUT.getErrorMessage(),
                    ErrorMessageCreateUser.FIRST_NAME.getErrorMessage()
                )
            );
        }
    }

    private static void validateLastName(String lastName) {
        logger.info("validating the lastName field");
        if (!isValidateString(lastName)) {
            throw new BadRequestException(
                String.format(
                    ErrorMessageCreateUser.MISSING_REQUIRED_INPUT.getErrorMessage(),
                    ErrorMessageCreateUser.LAST_NAME.getErrorMessage()
                )
            );
        }
    }

    private static void validateEmail(String email) {
        logger.info("validating the email field");
        if (!isValidateString(email)) {
            throw new BadRequestException(
                String.format(
                    ErrorMessageCreateUser.MISSING_REQUIRED_INPUT.getErrorMessage(),
                    ErrorMessageCreateUser.EMAIL.getErrorMessage()
                )
            );
        }
        if (!isValidEmail(email)) {
            throw new BadRequestException(
                String.format(
                    ErrorMessageCreateUser.INVALIDATE_INPUT.getErrorMessage(),
                    ErrorMessageCreateUser.EMAIL.getErrorMessage(),
                    ErrorMessageCreateUser.CORRECT_FORMAT_EMAIL.getErrorMessage()
                )
            );
        }
    }

    private static void validatePassword(String password) {
        logger.info("validating the password field");
        if (!isValidateString(password)) {
            throw new BadRequestException(
                String.format(
                    ErrorMessageCreateUser.MISSING_REQUIRED_INPUT.getErrorMessage(),
                    ErrorMessageCreateUser.PASSWORD.getErrorMessage()
                )
            );
        }
        if (!isValidatePassword(password)) {
            throw new BadRequestException(
                String.format(
                    ErrorMessageCreateUser.INVALIDATE_INPUT.getErrorMessage(),
                    ErrorMessageCreateUser.PASSWORD.getErrorMessage(),
                    ErrorMessageCreateUser.CORRECT_FORMAT_PASSWORD.getErrorMessage()
                )
            );
        }
    }

    private static void validateStatus(Integer status) {
        logger.info("Validating the status field");
        if (status == null) {
            throw new BadRequestException(
                String.format(
                    ErrorMessageCreateUser.MISSING_REQUIRED_INPUT.getErrorMessage(),
                    ErrorMessageCreateUser.STATUS.getErrorMessage()
                )
            );
        }
        if( status < 0 || status > 1) {
            throw new BadRequestException(
                String.format(
                    ErrorMessageCreateUser.INVALIDATE_INPUT.getErrorMessage(),
                    ErrorMessageCreateUser.STATUS.getErrorMessage(),
                    ErrorMessageCreateUser.CORRECT_FORMAT_STATUS.getErrorMessage()
                )
            );
        }
    }
}
