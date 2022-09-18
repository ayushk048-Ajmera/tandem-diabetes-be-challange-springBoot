package com.backendchallenge.services;

import com.backendchallenge.dtos.UserRequestDTO;
import com.backendchallenge.dtos.UserResponseDTO;

import java.util.List;


public interface IUserService {
	UserResponseDTO createUser(UserRequestDTO userRequestDTO) throws Exception;

	UserResponseDTO getUser(String emailAddress);

	List<UserResponseDTO> getUsers();
}
