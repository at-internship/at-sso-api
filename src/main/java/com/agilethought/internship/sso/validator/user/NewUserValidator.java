package com.agilethought.internship.sso.validator.user;

import com.agilethought.internship.sso.exception.BadRequestException;
import com.agilethought.internship.sso.model.User;
import com.agilethought.internship.sso.repository.RepositoryApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Locale;

import static com.agilethought.internship.sso.exception.errorhandling.ErrorMessage.ALREADY_EXISTING_EMAIL;

@Service
public class NewUserValidator {
    @Autowired
    private RepositoryApplication repositoryApplication;

    @Autowired
    private UserDataValidator userDataValidator;

    public void validate(User user) {
        userDataValidator.validate(user);
        validateUniqueEmail(user.getEmail().toLowerCase());
    }

    private void validateUniqueEmail(String email) {
        if (repositoryApplication.existsByEmail(email)) {
            throw new BadRequestException(
                String.format(ALREADY_EXISTING_EMAIL, email)
            );
        }
    }
}
