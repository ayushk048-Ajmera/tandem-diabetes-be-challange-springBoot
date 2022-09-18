package com.backendchallenge.dtos;

import lombok.Data;

@Data
public class UserResponseDTO {
	private String userId;
	private String name;
	private String phoneNumber;
	private String emailAddress;
}
