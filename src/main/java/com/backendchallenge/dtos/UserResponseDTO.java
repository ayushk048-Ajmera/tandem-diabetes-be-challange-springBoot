package com.backendchallenge.dtos;

import com.backendchallenge.entities.UserEntity;
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

	public static UserResponseDTO toDto(UserEntity userEntity) {
		return UserResponseDTO.builder()
				.userId(userEntity.getId())
				.name(String.format("%s %s %s", userEntity.getFirstName(),userEntity.getMiddleName(), userEntity.getLastName()))
				.phoneNumber(userEntity.getPhoneNumber())
				.emailAddress(userEntity.getEmailAddress())
				.build();
	}
}
