package com.agilethought.internship.sso.controller;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.agilethought.internship.sso.controller.UserController;
import com.agilethought.internship.sso.services.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private UserService userService;

	private static final String REQUEST_MAPPING = "/api/v1";

	@Test
	public void testDeleteUserById() throws Exception {
		String deleteMapping = "/users/1234";
		doNothing().when(userService).deleteUserById(anyString());
		mockMvc.perform(delete(REQUEST_MAPPING + deleteMapping).contentType(APPLICATION_JSON))
				.andExpect(status().isNoContent());

	}
}
