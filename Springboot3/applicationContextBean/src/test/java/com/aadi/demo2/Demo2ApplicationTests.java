package com.aadi.demo2;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import lombok.RequiredArgsConstructor;

@SpringBootTest
@RequiredArgsConstructor
class Demo2ApplicationTests {

	private final ApplicationContext context;

	@Test
	void contextLoads() {
	}

	@Test
	void checkIfUserBeanIsRegisteredInApplicationContext() {
		assertNotNull(context.getBean(User.class));
	}

}
