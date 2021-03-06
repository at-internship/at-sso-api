package com.agilethought.internship.sso.validator.user;

import com.agilethought.internship.sso.exception.BadRequestException;
import com.agilethought.internship.sso.exception.NotFoundException;
import com.agilethought.internship.sso.model.User;
import com.agilethought.internship.sso.repository.RepositoryApplication;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

public class UpdateUserValidatorTest {

	@Mock
	private RepositoryApplication repositoryApplication;

	@Mock
	private UserDataValidator userDataValidator;

	@InjectMocks
	private UpdateUserValidator updateUserValidator;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testUpdateUserByIdWithMissingId() {

		User mockUpdateUser = new User();

		Exception thrownException = assertThrows(BadRequestException.class, () -> {
			updateUserValidator.validate(mockUpdateUser);
		});
		assertEquals("Required field id is missing.", thrownException.getMessage());
	}

	@Test
	public void testUpdateUserByIdWithIdNotFound() {

		User mockUpdateUser = new User();
		mockUpdateUser.setId("mockedId");

		when(repositoryApplication.findById(anyString())).thenReturn(Optional.empty());
		Exception thrownException = assertThrows(NotFoundException.class, () -> {
			updateUserValidator.validate(mockUpdateUser);
		});
		assertEquals("User was not found with the given id: mockedId", thrownException.getMessage());
	}

	@Test
	public void testUpdateUserByIdWithEmailAlreadyTaken() {

		// A user with email "current_email@example.com" tries
		// to update its email to "my_email@example.com", but
		// it's already taken by another user.
		User mockUpdateRequest = new User();
		mockUpdateRequest.setId("mockedUserToUpdateId");
		mockUpdateRequest.setEmail("my_email@example.com");

		User mockOwnerOfEmail = new User();
		mockOwnerOfEmail.setId("mockedEmailOwnerId");
		mockOwnerOfEmail.setEmail("my_email@example.com");

		User mockUserToUpdate = new User();
		mockUserToUpdate.setId("mockedUserToUpdateId");
		mockUserToUpdate.setEmail("current_email@example.com");

		when(repositoryApplication.findById("mockedUserToUpdateId")).thenReturn(Optional.of(mockUserToUpdate));
		doNothing().when(userDataValidator).validate(any());
		when(repositoryApplication.existsByEmail(anyString())).thenReturn(true);
		Exception thrownException = assertThrows(BadRequestException.class, () -> {
			updateUserValidator.validate(mockUpdateRequest);
		});
		assertEquals("email my_email@example.com is already in use, try with another one or login.",
				thrownException.getMessage());
	}

	@Test
	public void testUpdateUserByIdSuccessfullyWithNewEmail() {

		User mockUpdateUser = new User();
		mockUpdateUser.setId("1234");
		mockUpdateUser.setEmail("new_unique_email@example.com");

		when(repositoryApplication.findById(anyString())).thenReturn(Optional.of(mockUpdateUser));
		doNothing().when(userDataValidator).validate(any());
		when(repositoryApplication.existsByEmail(anyString())).thenReturn(false);
		assertDoesNotThrow(() -> {
			updateUserValidator.validate(mockUpdateUser);
		});
	}

	@Test
	public void testUpdateUserByIdWithSameEmail() {

		User mockUpdateUser = new User();
		mockUpdateUser.setId("1234");
		mockUpdateUser.setEmail("my_same_email@example.com");

		when(repositoryApplication.findById(anyString())).thenReturn(Optional.of(mockUpdateUser));
		doNothing().when(userDataValidator).validate(any());
		when(repositoryApplication.existsByEmail(anyString())).thenReturn(true);
		assertDoesNotThrow(() -> {
			updateUserValidator.validate(mockUpdateUser);
		});
	}
}