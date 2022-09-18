package com.backendchallenge.models;

import lombok.Data;

@Data
public class UserModel {
	private String firstName;
	private String middleName;
	private String lastName;
	private String phoneNumber;
	private String emailAddress;
}
