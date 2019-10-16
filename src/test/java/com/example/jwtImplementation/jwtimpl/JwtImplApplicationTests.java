package com.example.jwtImplementation.jwtimpl;

import com.example.jwtImplementation.jwtimpl.controller.Controller;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

@RunWith(SpringRunner.class)
@SpringBootTest
public class JwtImplApplicationTests {

	@Autowired
	WebTestClient webTestClient;

	@Test
	public void contextLoads() {
	}

}
