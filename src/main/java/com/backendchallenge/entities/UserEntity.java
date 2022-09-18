package com.backendchallenge.entities;

import com.azure.core.annotation.Generated;
import com.azure.spring.data.cosmos.core.mapping.Container;
import com.azure.spring.data.cosmos.core.mapping.GeneratedValue;
import com.azure.spring.data.cosmos.core.mapping.PartitionKey;
import com.backendchallenge.dtos.UserRequestDTO;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.UUID;

@Data
@Builder
@Container(containerName = "AyushTandemUsersSpringBoot")
public class UserEntity {

	@Id
	private String id;
	private String firstName;
	private String lastName;
	private String middleName;
	private String phoneNumber;

	@PartitionKey
	private String emailAddress;
}
