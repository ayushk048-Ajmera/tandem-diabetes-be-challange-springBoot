package com.backendchallenge.controllers;

import com.backendchallenge.dtos.UserRequestDTO;
import com.backendchallenge.dtos.UserResponseDTO;
import com.backendchallenge.services.IUserService;
import com.backendchallenge.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Collections;
import java.util.List;

@Valid
@RestController
@RequestMapping("/api/users")
@Slf4j
public class UsersController {

	@Autowired
	IUserService userService;

	@PostMapping
	public ResponseEntity<UserResponseDTO> createUser(@Valid @RequestBody UserRequestDTO user) throws Exception {
		log.info("Creating user for {}", StringUtils.obfuscateEmailAddress(user.getEmailAddress()));

		UserResponseDTO response = userService.createUser(user);

		log.info("Created user for {}", StringUtils.obfuscateEmailAddress(user.getEmailAddress()));
		return ResponseEntity.ok(response);
	}

	@GetMapping
	public ResponseEntity<List<UserResponseDTO>> getUsers(@RequestParam(required = false) String email) {
		log.info("Getting user information for ");

		if (org.apache.commons.lang3.StringUtils.isEmpty(email)) {
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.add("ContentType", "application/json");
			List<UserResponseDTO> users = userService.getUsers();
			return new ResponseEntity<>(users, responseHeaders, HttpStatus.OK);
		} else {
			UserResponseDTO user = userService.getUser(email);
			return ResponseEntity.ok(Collections.singletonList(user));
		}
	}
}
