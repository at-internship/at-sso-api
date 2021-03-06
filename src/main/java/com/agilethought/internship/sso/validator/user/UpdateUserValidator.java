package com.agilethought.internship.sso.validator.user;

import com.agilethought.internship.sso.exception.BadRequestException;
import com.agilethought.internship.sso.exception.NotFoundException;
import com.agilethought.internship.sso.model.User;
import com.agilethought.internship.sso.repository.RepositoryApplication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

import static com.agilethought.internship.sso.exception.errorhandling.ErrorMessage.*;
import static com.agilethought.internship.sso.validator.ValidationUtils.isValidString;

@Service
public class UpdateUserValidator {

    @Autowired
    private RepositoryApplication userRepository;

    @Autowired
    private UserDataValidator userDataValidator;

    public void validate(User user) {

        // If the given id matches a user, we need the email
        // of that user for the unique email validation.
        String emailOfUserToUpdate = validateGivenId(user.getId());
        userDataValidator.validate(user);
        validateUniqueEmail(user.getEmail(), emailOfUserToUpdate);

    }

    private String validateGivenId(String id) {

        if (!isValidString(id))
            throw new BadRequestException(
                    String.format(MISSING_REQUIRED_INPUT, ID)
            );
        Optional<User> userFoundById = userRepository.findById(id);
        if (!userFoundById.isPresent())
            throw new NotFoundException(
                    String.format(NOT_FOUND_RESOURCE, USER, id)
            );
        return userFoundById.get().getEmail();

    }

    private void validateUniqueEmail(String emailInRequest, String emailOfUserToUpdate) {

        // If the email in the request exists already, we verify it
        // belongs to the user being updated. Otherwise, it
        // belongs to somebody else and the request is rejected.
        if (userRepository.existsByEmail(emailInRequest) && !emailInRequest.equals(emailOfUserToUpdate) ) {
            throw new BadRequestException(
                    String.format(ALREADY_EXISTING_EMAIL, emailInRequest)
            );
        }
    }

}