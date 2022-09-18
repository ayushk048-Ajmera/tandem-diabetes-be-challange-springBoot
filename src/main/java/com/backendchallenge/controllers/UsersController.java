package com.backendchallenge.controllers;

import com.backendchallenge.dtos.UserRequestDTO;
import com.backendchallenge.dtos.UserResponseDTO;
import com.backendchallenge.services.IUserService;
import com.backendchallenge.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
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
	public ResponseEntity getUsers(@RequestParam(required = false) String email) {
		log.info("Getting user information for ");

		if (org.apache.commons.lang3.StringUtils.isEmpty(email)) {
			List<UserResponseDTO> users = userService.getUsers();
			return ResponseEntity.ok(users);
		} else {
			UserResponseDTO user = userService.getUser(email);
			if (user == null) {
				throw new ResponseStatusException(
						HttpStatus.NOT_FOUND, "User not found");
			}
			return ResponseEntity.ok(user);
		}
	}
}
