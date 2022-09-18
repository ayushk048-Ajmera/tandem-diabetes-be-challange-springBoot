package com.backendchallenge.dtos;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Data
public class UserRequestDTO {
	@NotNull
	private String firstName;

	@NotNull
	private String middleName;

	@NotNull
	private String lastName;

	@NotNull
	private String phoneNumber;

	@NotNull
	@Email
	private String emailAddress;
}
