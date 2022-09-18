package com.backendchallenge.dtos;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
@RequiredArgsConstructor
public class UserRequestDTO {
	@NotBlank
	private String firstName;

	@NotBlank
	private String middleName;

	@NotBlank
	private String lastName;

	@NotBlank
	private String phoneNumber;

	@NotBlank
	@Email
	private String emailAddress;
}
