package com.backendchallenge;

import static org.assertj.core.api.Assertions.assertThat;

import com.backendchallenge.controllers.UsersController;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BackendChallengeApplicationTest {
	@Autowired
	private UsersController controller;

	@Test
	void contextLoads() {
		assertThat(controller).isNotNull();
	}
}
