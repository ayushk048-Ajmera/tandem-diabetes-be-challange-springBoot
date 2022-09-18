package com.backendchallenge.repository;

import com.azure.spring.data.cosmos.repository.CosmosRepository;
import com.azure.spring.data.cosmos.repository.Query;
import com.backendchallenge.entities.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface UserRepository extends CosmosRepository<UserEntity, String> {
    // Query for all documents
    @Query(value = "SELECT * FROM c")
    List<UserEntity> getAllUsers();

    UserEntity findByEmailAddress(String email);
}