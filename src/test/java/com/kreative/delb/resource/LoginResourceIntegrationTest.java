package com.kreative.delb.resource;

import com.kreative.delb.model.User;
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
	}

	@Test
	public void login_ko() throws Exception {
		User user = userDAO.findAnyone();
		//
		mockMvc.perform(post("/login")
				.contentType("application/x-www-form-urlencoded")
				.param("username", user.getUsername())
				.param("password", user.getPassword() + "SB"))
				.andExpect(status().isUnauthorized());
	}

	@Test
	public void login_ok() throws Exception {
		User user = userDAO.findAnyone();
		//
		mockMvc.perform(post("/login")
				.contentType("application/x-www-form-urlencoded")
				.param("username", user.getUsername())
				.param("password", user.getPassword()))
				.andExpect(status().isOk());
	}
}
