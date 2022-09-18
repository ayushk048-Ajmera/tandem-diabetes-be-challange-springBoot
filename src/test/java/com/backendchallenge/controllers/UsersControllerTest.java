package com.backendchallenge.controllers;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class UsersControllerTest {
	@Autowired
	private MockMvc mockMvc;

	@Test
	void shouldReturnBadRequestWhenEmailIsInvalid() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.get("/api/users?email=invalid_email")
		).andExpect(status().isBadRequest());
	}

	@Test
	void shouldReturnNotFoundWhenUserWithEmailDoesNotExist() throws Exception {
		mockMvc.perform(
				MockMvcRequestBuilders.get("/api/users?email=fakeemail@fakesite.com")
		).andExpect(status().isNotFound());
	}
}
