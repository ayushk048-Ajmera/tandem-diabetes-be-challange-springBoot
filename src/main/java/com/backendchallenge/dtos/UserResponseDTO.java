package com.backendchallenge.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
	private String userId;
	private String name;
	private String phoneNumber;
	private String emailAddress;
}
