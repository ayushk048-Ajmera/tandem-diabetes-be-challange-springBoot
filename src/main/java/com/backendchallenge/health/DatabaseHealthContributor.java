package com.backendchallenge.health;

import com.backendchallenge.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthContributor;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

@Component("CosmosDB")
public class DatabaseHealthContributor implements HealthIndicator, HealthContributor {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Health health() {
        try{
            userRepository.count();
        } catch (Exception ex) {
            return Health.outOfService().withException(ex).build();
        }
        return Health.up().build();
    }
}
