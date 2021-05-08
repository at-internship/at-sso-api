package com.agilethought.internship.sso.validator.user;

import com.agilethought.internship.sso.exception.BadRequestException;
import com.agilethought.internship.sso.model.User;
import com.agilethought.internship.sso.repository.RepositoryApplication;
import org.junit.Before;
import org.junit.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

public class NewUserValidatorTest {

	@Mock
	private RepositoryApplication repositoryApplication;

	@Mock
	private UserDataValidator userDataValidator;

	@InjectMocks
	private NewUserValidator newUserValidator;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testCreateUserWithEmailAlreadyTaken() {

		User mockCreateUser = new User();
		mockCreateUser.setEmail("email_already_taken@example.com");

		doNothing().when(userDataValidator).validate(any());
		when(repositoryApplication.existsByEmail(anyString())).thenReturn(true);
		Exception thrownException = assertThrows(BadRequestException.class, () -> {
			newUserValidator.validate(mockCreateUser);
		});
		assertEquals(
				String.format("email %s is already in use, try with another one or login.", mockCreateUser.getEmail()),
				thrownException.getMessage());
	}

	@Test
	public void testCreateUserWithNewEmail() {

		User mockCreateUser = new User();
		mockCreateUser.setEmail("new_unique_email@example.com");

		doNothing().when(userDataValidator).validate(any());
		when(repositoryApplication.existsByEmail(anyString())).thenReturn(false);
		assertDoesNotThrow(() -> {
			newUserValidator.validate(mockCreateUser);
		});
	}
}