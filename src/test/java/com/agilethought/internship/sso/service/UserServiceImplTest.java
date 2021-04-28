package com.agilethought.internship.sso.service;

import static com.agilethought.internship.sso.exception.ErrorMessage.NOT_FOUND_RESOURCE;
import static com.agilethought.internship.sso.exception.ErrorMessage.USER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.agilethought.internship.sso.exception.NotFoundException;
import com.agilethought.internship.sso.model.User;
import com.agilethought.internship.sso.repository.RepositoryApplication;
import com.agilethought.internship.sso.services.implementation.UserServiceImpl;

import ma.glasnost.orika.MapperFacade;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest {

	@Mock
	private RepositoryApplication RepositoryApplication;

	@Mock
	private MapperFacade orikaMapperFacade;

	@InjectMocks
	private UserServiceImpl userService;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testDeleteUserByIdSuccessfully() {

		when(RepositoryApplication.findById(anyString())).thenReturn(Optional.of(new User()));
		doNothing().when(RepositoryApplication).deleteById(anyString());
		userService.deleteUserById(anyString());
		verify(RepositoryApplication).deleteById(anyString());

	}

	@Test
	public void testDeleteUserByIdWithIdNotFound() {

		when(RepositoryApplication.findById(anyString())).thenReturn(Optional.empty());
		doNothing().when(RepositoryApplication).deleteById(anyString());
		NotFoundException thrownException = assertThrows(NotFoundException.class,
				() -> userService.deleteUserById(anyString()));
		assertEquals(String.format(NOT_FOUND_RESOURCE, USER, ""), thrownException.getMessage());

	}

}
