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

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserServiceImpl implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserResponseDTO createUser(UserRequestDTO userRequestDTO) {
        log.info("Creating user {}", StringUtils.obfuscateEmailAddress(userRequestDTO.getEmailAddress()));

        UserEntity entity = UserMapper.MAPPER.toEntity(userRequestDTO);
        entity.setId(UUID.randomUUID().toString());
        UserEntity responseEntity = userRepository.save(entity);

        log.info("Created user {}", StringUtils.obfuscateEmailAddress(userRequestDTO.getEmailAddress()));
        return UserMapper.MAPPER.toResponseDTO(responseEntity);

    }

    @Override
    public UserResponseDTO getUser(String emailAddress) {
        log.info("Getting user information for {}", StringUtils.obfuscateEmailAddress(emailAddress));

        UserEntity responseEntity = userRepository.findByEmailAddress(emailAddress);

        log.info("Got user information for {}", StringUtils.obfuscateEmailAddress(emailAddress));
        return responseEntity == null ? null : UserMapper.MAPPER.toResponseDTO(responseEntity);
    }

    @Override
    public List<UserResponseDTO> getUsers() {
        log.info("Getting all users");

        Iterable<UserEntity> all = userRepository.findAll();
        List<UserEntity> allUsers = new ArrayList<>();
        all.forEach(allUsers::add);

        log.info("Got all users");
        return allUsers.isEmpty() ? Collections.emptyList() : allUsers.stream()
                .map(UserMapper.MAPPER::toResponseDTO).collect(Collectors.toList());
    }
}
