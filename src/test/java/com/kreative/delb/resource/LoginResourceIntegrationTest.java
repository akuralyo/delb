package com.kreative.delb.resource;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class LoginResourceIntegrationTest extends AbstractIntegrationtest {

	@Before
	public void before() throws Exception {
		super.before();
		userDAO.createAdmin();
	}

	@Test
	public void login_ko() throws Exception {
		// Récupération du token
		mockMvc.perform(post("/login")
				.contentType("application/x-www-form-urlencoded")
				.param("username", "ADMIN")
				.param("password", "ADMINFF"))
				.andExpect(status().isUnauthorized());
	}

	@Test
	public void login_ok() throws Exception {
		// Récupération du token
		mockMvc.perform(post("/login")
				.contentType("application/x-www-form-urlencoded")
				.param("username", "ADMIN")
				.param("password", "ADMIN"))
				.andExpect(status().isOk());
	}
}
