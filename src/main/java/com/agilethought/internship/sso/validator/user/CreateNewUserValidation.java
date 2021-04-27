package com.agilethought.internship.sso.validator.user;

import com.agilethought.internship.sso.exception.BadRequestException;
import com.agilethought.internship.sso.model.User;
import com.agilethought.internship.sso.repository.RepositoryApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import static com.agilethought.internship.sso.exception.errorMessages.ErrorMessageCreateUser.ALREADY_EXISTING_EMAIL;

@Service
public class CreateNewUserValidation {

    private static final String PATH_API = "/api/v1/users/id";

    @Autowired
    private RepositoryApplication repositoryApplication;

    @Autowired
    private UserValidator userValidator;

    public void validate(User user) {
        this.userValidator.validate(user);
        validateUniqueEmail(user.getEmail());
    }

    private void validateUniqueEmail(String email) {
        if (repositoryApplication.existsByEmail(email)) {
            throw new BadRequestException(
                String.format(
                    ALREADY_EXISTING_EMAIL.getErrorMessage(),
                    email
                ),PATH_API, HttpStatus.BAD_REQUEST
            );
        }
    }
}
