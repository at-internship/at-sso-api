package com.agilethought.internship.sso.services;

import com.agilethought.internship.sso.dto.*;
import com.agilethought.internship.sso.exception.UnauthorizedException;
import com.agilethought.internship.sso.model.User;
import com.agilethought.internship.sso.repository.RepositoryApplication;
import com.agilethought.internship.sso.validator.user.LoginValidator;
import com.agilethought.internship.sso.validator.user.NewUserValidator;
import com.agilethought.internship.sso.validator.user.UpdateUserValidator;
import ma.glasnost.orika.MapperFacade;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static com.agilethought.internship.sso.exception.ErrorMessage.INVALID_CREDENTIALS;
import static com.agilethought.internship.sso.exception.ErrorMessage.UNAVAILABLE_ENTITY;
import static com.agilethought.internship.sso.exception.ErrorMessage.USER;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

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

        when(
            orikaMapperFacade.map(any(NewUserRequest.class), any()))
            .thenReturn(mockCreateUser
            );
        doNothing().when(newUserValidator).validate(any());
        when(repositoryApplication.existsByEmail(anyString())).thenReturn(false);
        when(repositoryApplication.save(any())).thenReturn(new User());
        when(orikaMapperFacade.map(any(User.class), any())).thenReturn(new NewUserResponse());
        assertNotNull(serviceApplicationimpl.createUser(new NewUserRequest()));
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
    
    @Test
    public void testLoginUserWithValidCredentialsAndAvailable() {

        // Given
        LoginRequest mockLoginRequest = new LoginRequest();
        mockLoginRequest.setEmail("");
        mockLoginRequest.setPassword("");

        List<User> mockUsersList = new ArrayList<>();
        mockUsersList.add(new User());
        mockUsersList.get(0).setStatus(1);

        // Then
        doNothing().when(loginValidator).validate(any());
        when(repositoryApplication.findUserWithCredentials(anyString(), anyString()))
                .thenReturn(mockUsersList);
        when(orikaMapperFacade.map(any(), any())).thenReturn(new LoginResponse());
        LoginResponse testResult = serviceApplicationimpl.loginUser(mockLoginRequest);
        assertNotNull(testResult);

    }

    @Test
    public void testLoginUserWithValidCredentialsAndUnavailable() {

        // Given
        LoginRequest mockLoginRequest = new LoginRequest();
        mockLoginRequest.setEmail("");
        mockLoginRequest.setPassword("");

        List<User> mockUsersList = new ArrayList<>();
        mockUsersList.add(new User());
        mockUsersList.get(0).setStatus(0);

        // Then
        doNothing().when(loginValidator).validate(any());
        when(repositoryApplication.findUserWithCredentials(anyString(), anyString()))
                .thenReturn(mockUsersList);
        UnauthorizedException thrownException = assertThrows(
                UnauthorizedException.class,
                () -> serviceApplicationimpl.loginUser(mockLoginRequest)
        );
        assertEquals(
                String.format(UNAVAILABLE_ENTITY, USER),
                thrownException.getMessage()
        );

    }

    @Test
    public void testLoginUserWithInvalidCredentials() {

        // Given
        LoginRequest mockLoginRequest = new LoginRequest();
        mockLoginRequest.setEmail("");
        mockLoginRequest.setPassword("");

        // Then
        doNothing().when(loginValidator).validate(any());
        when(repositoryApplication.findUserWithCredentials(anyString(), anyString()))
                .thenReturn(new ArrayList<>());
        UnauthorizedException thrownException = assertThrows(
                UnauthorizedException.class,
                () -> serviceApplicationimpl.loginUser(mockLoginRequest)
        );
        assertEquals(
                INVALID_CREDENTIALS,
                thrownException.getMessage()
        );
    }

}