package com.backendchallenge.repository;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.backendchallenge.entities.UserEntity;


public interface UserRepository extends CosmosRepository<UserEntity, String> {

    UserEntity findByEmailAddress(String email);
}