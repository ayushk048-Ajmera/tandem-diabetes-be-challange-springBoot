package com.backendchallenge.services;

import com.backendchallenge.dtos.UserRequestDTO;
import com.backendchallenge.dtos.UserResponseDTO;
import com.backendchallenge.entities.UserEntity;
import com.backendchallenge.mapper.UserMapper;
import com.backendchallenge.repository.UserRepository;
import com.backendchallenge.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        log.info("Creating user {}", StringUtils.obfuscateEmailAddress(userRequestDTO.getEmailAddress()));

        UserEntity entity = UserEntity.toEntity(userRequestDTO);
        UserEntity responseEntity = userRepository.save(entity);

        log.info("Created user {}", StringUtils.obfuscateEmailAddress(userRequestDTO.getEmailAddress()));
        return UserResponseDTO.toDto(responseEntity);

    }

    @Override
    public UserResponseDTO getUser(String emailAddress) {
        log.info("Getting user information for {}", StringUtils.obfuscateEmailAddress(emailAddress));

        UserEntity responseEntity = userRepository.findById(emailAddress).orElse(null);

        log.info("Got user information for {}", StringUtils.obfuscateEmailAddress(emailAddress));
        return responseEntity == null ? null : UserMapper.MAPPER.toResponseDTO(responseEntity);
    }

    @Override
    public List<UserResponseDTO> getUsers() {
        log.info("Getting user information for ");

        List<UserEntity> allUsers = userRepository.getAllUsers();

        log.info("Got user information for");
        return allUsers.isEmpty() ? Collections.emptyList() : allUsers.stream().map(UserResponseDTO::toDto).collect(Collectors.toList());
    }
}
