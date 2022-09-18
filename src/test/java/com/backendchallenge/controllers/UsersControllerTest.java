package com.backendchallenge.controllers;

import com.backendchallenge.dtos.UserRequestDTO;
import com.backendchallenge.dtos.UserResponseDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
class UsersControllerTest {
	@Autowired
	private MockMvc mockMvc;
	private final ObjectMapper mapper;
	private final int randomNumber;

	public UsersControllerTest() {
		mapper = new ObjectMapper();
		randomNumber = new Random().nextInt(1000);
	}

	@Test
	void shouldReturnBadRequestWhenEmailIsInvalidWhileCreatingUser() throws Exception {
		UserRequestDTO createUserRequest = getMockUser();
		createUserRequest.setEmailAddress("invalid_email");

		mockMvc.perform(
				MockMvcRequestBuilders.post("/api/users")
						.content(mapper.writeValueAsString(createUserRequest))
						.contentType(MediaType.APPLICATION_JSON)
		).andExpect(status().isBadRequest());
	}

	@Test
	void shouldCreateANewUser() throws Exception {
		UserRequestDTO createUserRequest = getMockUser();

		MvcResult result = mockMvc.perform(
				MockMvcRequestBuilders.post("/api/users")
						.content(mapper.writeValueAsString(createUserRequest))
						.contentType(MediaType.APPLICATION_JSON)
		).andExpect(status().isOk()).andReturn();

		String responseJson = result.getResponse().getContentAsString();
		UserResponseDTO createdUser = mapper.readValue(responseJson, UserResponseDTO.class);

		assertEquals(createUserRequest.getEmailAddress(), createdUser.getEmailAddress());
		assertEquals("FirstName MiddleName LastName", createdUser.getName());


		// Creating a user with same name email should give conflict
		mockMvc.perform(
				MockMvcRequestBuilders.post("/api/users?email=" + createUserRequest.getEmailAddress())
						.content(mapper.writeValueAsString(createUserRequest))
						.contentType(MediaType.APPLICATION_JSON)
		).andExpect(status().isConflict());


		// Should get created user
		MvcResult getUserResult = mockMvc.perform(
				MockMvcRequestBuilders.get("/api/users?email=" + createUserRequest.getEmailAddress())
		).andExpect(status().isOk()).andReturn();

		String getUserJson = getUserResult.getResponse().getContentAsString();
		UserResponseDTO userWithEmail = mapper.readValue(getUserJson, UserResponseDTO.class);

		assertEquals(createUserRequest.getEmailAddress(), userWithEmail.getEmailAddress());
		assertEquals("FirstName MiddleName LastName", userWithEmail.getName());
	}

	private UserRequestDTO getMockUser() {
		UserRequestDTO mockUser = new UserRequestDTO();
		mockUser.setFirstName("FirstName");
		mockUser.setMiddleName("MiddleName");
		mockUser.setLastName("LastName");
		mockUser.setPhoneNumber("+100000000");
		mockUser.setEmailAddress("valid_email" + randomNumber + "@domain.com");

		return mockUser;
	}

	@Test
	void shouldGetMultipleUsers() throws Exception {
		MvcResult result = mockMvc.perform(
				MockMvcRequestBuilders.get("/api/users")
		).andExpect(status().isOk()).andReturn();

		String responseJson = result.getResponse().getContentAsString();
		List<UserResponseDTO> users = mapper.readValue(responseJson, new TypeReference<List<UserResponseDTO>>() {
		});

		assertTrue(users.size() > 1);
	}

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
