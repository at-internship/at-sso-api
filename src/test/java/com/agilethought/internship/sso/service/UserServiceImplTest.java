package com.agilethought.internship.sso.service;

import static com.agilethought.internship.sso.exception.ErrorMessage.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import com.agilethought.internship.sso.validator.user.LoginValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.agilethought.internship.sso.dto.LoginRequest;
import com.agilethought.internship.sso.dto.LoginResponse;
import com.agilethought.internship.sso.exception.UnauthorizedException;
import com.agilethought.internship.sso.model.User;
import com.agilethought.internship.sso.services.UserServiceImpl;
import com.agilethought.internship.sso.repository.UserRepository;

import ma.glasnost.orika.MapperFacade;

@RunWith(SpringJUnit4ClassRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private MapperFacade orikaMapperFacade;

    @Mock
    private LoginValidator loginValidator;

    @InjectMocks
    private UserServiceImpl userService;

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
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
        when(userRepository.findUserWithCredentials(anyString(), anyString()))
                .thenReturn(mockUsersList);
        when(orikaMapperFacade.map(any(), any())).thenReturn(new LoginResponse());
        LoginResponse testResult = userService.loginUser(mockLoginRequest);
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
        when(userRepository.findUserWithCredentials(anyString(), anyString()))
                .thenReturn(mockUsersList);
        UnauthorizedException thrownException = assertThrows(
                UnauthorizedException.class,
                () -> userService.loginUser(mockLoginRequest)
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
        when(userRepository.findUserWithCredentials(anyString(), anyString()))
                .thenReturn(new ArrayList<>());
        UnauthorizedException thrownException = assertThrows(
                UnauthorizedException.class,
                () -> userService.loginUser(mockLoginRequest)
        );
        assertEquals(
                INVALID_CREDENTIALS,
                thrownException.getMessage()
        );
    }

}