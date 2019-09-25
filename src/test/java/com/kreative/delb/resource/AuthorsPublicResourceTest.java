package com.kreative.delb.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kreative.delb.resource.constants.Api;
import com.kreative.delb.security.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthorsPublicResourceTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@Autowired
	private UserRepository userRepository;

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void findAll() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get(Api.API + Api.PUBLIC + Api.AUTHORS));
	}

	@Test
	public void get() {
	}
}
