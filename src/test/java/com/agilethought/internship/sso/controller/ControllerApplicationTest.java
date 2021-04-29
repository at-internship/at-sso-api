package com.agilethought.internship.sso.controller;

import com.agilethought.internship.sso.dto.LoginResponse;
import com.agilethought.internship.sso.dto.NewUserResponse;
import com.agilethought.internship.sso.dto.UpdateUserRequest;
import com.agilethought.internship.sso.dto.UpdateUserResponse;
import com.agilethought.internship.sso.dto.*;
import com.agilethought.internship.sso.services.ServiceApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(ControllerApplication.class)
public class ControllerApplicationTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ServiceApplication serviceApplication;

    private static final String REQUEST_MAPPING = "/api/v1";

    @Test
    public void itShouldGetAllUsers() throws Exception {
        String getMapping = "/users";
        when(serviceApplication.getAllUsers()).thenReturn(new ArrayList<>());
        mockMvc.perform(
                get(REQUEST_MAPPING + getMapping).contentType(APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

    @Test
    public void itShouldCreateNewUser() throws Exception {
        String postMapping = "/users";
        when(serviceApplication.createUser(any())).thenReturn(new NewUserResponse());
        mockMvc.perform(
                post(REQUEST_MAPPING + postMapping)
                        .content("{}")
                        .contentType(APPLICATION_JSON)
        ).andExpect(status().isCreated());

    }
    
    @Test
    public void itShouldDeleteUserById() throws Exception {

        String deleteMapping = "/users/1234";
        doNothing().when(serviceApplication).deleteUserById(anyString());
        mockMvc.perform(
                delete(REQUEST_MAPPING + deleteMapping)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isNoContent());

    }

    @Test
    public void testPutUser() throws Exception {

        String putMapping = "/users/1234";
        when(serviceApplication.updateUserById(any(UpdateUserRequest.class), anyString()))
                .thenReturn(new UpdateUserResponse());
        mockMvc.perform(
                put(REQUEST_MAPPING + putMapping)
                        .content("{}")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());
    }
    
    @Test
    public void testLoginUser() throws Exception {

        String postMapping = "/login";
        when(serviceApplication.loginUser(any())).thenReturn(new LoginResponse());
        mockMvc.perform(
                post(REQUEST_MAPPING + postMapping)
                        .content("{}")
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());

    }   
    public void testGetUserById() throws Exception {

        String getMapping = "/users/1234";
        when(serviceApplication.getUserById(anyString())).thenReturn(new UserDTO());
        mockMvc.perform(
                get(REQUEST_MAPPING + getMapping)
                .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());

    }

}