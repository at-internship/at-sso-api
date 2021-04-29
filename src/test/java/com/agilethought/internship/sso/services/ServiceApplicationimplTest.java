package com.agilethought.internship.sso.services;

import com.agilethought.internship.sso.dto.NewUserRequest;
import com.agilethought.internship.sso.dto.NewUserResponse;
import com.agilethought.internship.sso.dto.UserDTO;
import com.agilethought.internship.sso.model.User;
import com.agilethought.internship.sso.repository.RepositoryApplication;
import com.agilethought.internship.sso.validator.user.NewUserValidator;
import ma.glasnost.orika.MapperFacade;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

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

}