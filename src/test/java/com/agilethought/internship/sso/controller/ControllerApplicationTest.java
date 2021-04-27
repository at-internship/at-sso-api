package com.agilethought.internship.sso.controller;

import com.agilethought.internship.sso.domain.NewUserRequest;
import com.agilethought.internship.sso.domain.NewUserResponse;
import com.agilethought.internship.sso.domain.UpdateUserRequest;
import com.agilethought.internship.sso.domain.UpdateUserResponse;
import com.agilethought.internship.sso.services.ServiceApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
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
        String getMapping = "/user";
        when(serviceApplication.getUsers()).thenReturn(new ArrayList<>());
        mockMvc.perform(
                get(REQUEST_MAPPING + getMapping).contentType(APPLICATION_JSON)
        ).andExpect(status().isOk());
    }


    @Test
    public void itShouldCreateNewUser() throws Exception {
        String postMapping = "/user";
        when(serviceApplication.createUser(any(NewUserRequest.class))).thenReturn(new NewUserResponse());
        mockMvc.perform(
            post(REQUEST_MAPPING + postMapping)
                    .content("{}")
                    .contentType(APPLICATION_JSON)
        ).andExpect(status().isCreated());

    }

    @Test
    public void testPutUser() throws Exception {

        String putMapping = "/user/1234";
        when(serviceApplication.updateUser(any(UpdateUserRequest.class), anyString()))
                .thenReturn(new UpdateUserResponse());
        mockMvc.perform(
                put(REQUEST_MAPPING + putMapping)
                        .content("{}")
                        .contentType(APPLICATION_JSON))
                .andExpect(status().isOk());

    }
}