package com.backendchallenge.entities;

import com.azure.spring.data.cosmos.core.mapping.Container;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Container(containerName = "AyushTandemUsersSpringBoot")
public class UserEntity {

	private String id;
	private String firstName;
	private String lastName;
	private String middleName;
	private String phoneNumber;
	private String emailAddress;
}
