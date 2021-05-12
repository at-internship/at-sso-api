package com.agilethought.internship.sso.services;

import com.agilethought.internship.sso.dto.*;
import com.agilethought.internship.sso.exception.UnauthorizedException;
import com.agilethought.internship.sso.model.User;
import com.agilethought.internship.sso.repository.RepositoryApplication;
import com.agilethought.internship.sso.validator.user.LoginValidator;
import com.agilethought.internship.sso.validator.user.NewUserValidator;
import com.agilethought.internship.sso.validator.user.UpdateUserValidator;
import ma.glasnost.orika.MapperFacade;
import static com.agilethought.internship.sso.exception.errorhandling.ErrorMessage.NOT_FOUND_RESOURCE;
import static com.agilethought.internship.sso.exception.errorhandling.ErrorMessage.USER;
import static com.agilethought.internship.sso.exception.errorhandling.ErrorMessage.INVALID_CREDENTIALS;
import static com.agilethought.internship.sso.exception.errorhandling.ErrorMessage.UNAVAILABLE_ENTITY;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.agilethought.internship.sso.dto.NewUserRequest;
import com.agilethought.internship.sso.dto.NewUserResponse;
import com.agilethought.internship.sso.dto.UpdateUserRequest;
import com.agilethought.internship.sso.dto.UpdateUserResponse;
import com.agilethought.internship.sso.dto.UserDTO;
import com.agilethought.internship.sso.exception.NotFoundException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;

public class ServiceApplicationimplTest {
	@Mock
	private RepositoryApplication repositoryApplication;

	@Mock
	private MapperFacade orikaMapperFacade;

	@Mock
	private NewUserValidator newUserValidator;

    @Mock
    private UpdateUserValidator updateUserValidator;
    
    @Mock
    private LoginValidator loginValidator;

    @InjectMocks
    private ServiceApplicationimpl serviceApplicationimpl;

	@Before
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testGetAllUsers() {
		when(repositoryApplication.findAll()).thenReturn(new ArrayList<>());
		when(orikaMapperFacade.mapAsList(anyList(), any())).thenReturn(new ArrayList<>());
		List<UserDTO> testResult = serviceApplicationimpl.getAllUsers();
		assertNotNull(testResult);
	}

	@Test
	public void testCreateUserSuccessfully() {
		User mockCreateUser = new User();
		mockCreateUser.setFirstName("");
		mockCreateUser.setLastName("");
		mockCreateUser.setEmail("");

		when(orikaMapperFacade.map(any(NewUserRequest.class), any())).thenReturn(mockCreateUser);
		doNothing().when(newUserValidator).validate(any());
		when(repositoryApplication.existsByEmail(anyString())).thenReturn(false);
		when(repositoryApplication.save(any())).thenReturn(new User());
		when(orikaMapperFacade.map(any(User.class), any())).thenReturn(new NewUserResponse());
		assertNotNull(serviceApplicationimpl.createUser(new NewUserRequest()));
	}

	@Test
	public void testDeleteUserByIdSuccessfully() {

		when(repositoryApplication.findById(anyString())).thenReturn(Optional.of(new User()));
		doNothing().when(repositoryApplication).deleteById(anyString());
		serviceApplicationimpl.deleteUserById(anyString());
		verify(repositoryApplication).deleteById(anyString());

	}

	@Test
	public void testDeleteUserByIdWithIdNotFound() {

		when(repositoryApplication.findById(anyString())).thenReturn(Optional.empty());
		doNothing().when(repositoryApplication).deleteById(anyString());
		NotFoundException thrownException = assertThrows(NotFoundException.class,
				() -> serviceApplicationimpl.deleteUserById(anyString()));
		assertEquals(String.format(NOT_FOUND_RESOURCE, USER, ""), thrownException.getMessage());

	}

    @Test
    public void testUpdateUserByIdSuccessfully() {

        // Given
        User mockUpdateUser = new User();
        mockUpdateUser.setFirstName("");
        mockUpdateUser.setLastName("");
        mockUpdateUser.setEmail("");

        // Then
        when(orikaMapperFacade.map(any(UpdateUserRequest.class), any()))
                .thenReturn(mockUpdateUser);
        doNothing().when(updateUserValidator).validate(any());
        when(repositoryApplication.save(any())).thenReturn(new User());
        when(orikaMapperFacade.map(any(User.class), any())).thenReturn(new UpdateUserResponse());
        assertNotNull(serviceApplicationimpl.updateUserById(new UpdateUserRequest(), ""));
    }

    public void testGetUserByIdSuccessfully() {

        when(repositoryApplication.findById(anyString())).thenReturn(Optional.of(new User()));
        when(orikaMapperFacade.map(any(), any())).thenReturn(new UserDTO());
        UserDTO testResult = serviceApplicationimpl.getUserById(anyString());
        assertNotNull(testResult);
    }

    @Test
    public void testGetUserByIdWithIdNotFound() {

        when(repositoryApplication.findById(anyString())).thenReturn(Optional.empty());
        NotFoundException thrownException = assertThrows(
                NotFoundException.class,
                () -> serviceApplicationimpl.getUserById(anyString())
        );
        assertEquals(
                String.format(NOT_FOUND_RESOURCE, USER, ""),
                thrownException.getMessage()
        );
    }
}