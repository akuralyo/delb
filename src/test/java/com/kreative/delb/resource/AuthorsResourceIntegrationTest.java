package com.kreative.delb.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kreative.delb.dao.AuthorDAO;
import com.kreative.delb.dao.UserDAO;
import com.kreative.delb.resource.constants.Api;
import com.kreative.delb.resource.dto.AuthorDto;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;
import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthorsResourceIntegrationTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private AuthorDAO authorDAO;

	@Autowired
	private UserDAO userDAO;

	@Before
	public void setUp() throws Exception {
		authorDAO.deleteAll();
		userDAO.deleteAll();
	}

	private Login login = new Login("ADMIN", "ADMIN");

	@Test
	public void findAll_public_api() throws Exception {
		int nbElement = 5;
		authorDAO.initDb(nbElement);
		//
		MvcResult mvcRes = mockMvc.perform(MockMvcRequestBuilders.get(Api.API + Api.PUBLIC + Api.AUTHORS)).andReturn();
		List<AuthorDto> authorDtoList = parseResponse(mvcRes, ArrayList.class);
		//
		assertEquals(authorDtoList.size(), nbElement);
		try {
			assertNull(authorDtoList.get(0).getAdresse());
			assertTrue(false);
		} catch (ClassCastException c) {
			assertTrue(true);
		}
	}

	@Test
	@Ignore
	public void findAll_private_api() throws Exception {
		int nbElement = 5;
		authorDAO.initDb(nbElement);
		userDAO.createAdmin();
		//
		// Récupération du token
		MvcResult mvcResLogin = mockMvc.perform(MockMvcRequestBuilders.post("/login")
				.contentType("application/x-www-form-urlencoded")
				.param("username", "ADMIN")
				.param("password", "ADMIN"))
				.andReturn();
		// mvcResLogin.getResponse().getHeader();
		//
		MvcResult mvcRes = mockMvc.perform(MockMvcRequestBuilders.get(Api.API + Api.PRIVATE + Api.AUTHORS)).andReturn();
		List<AuthorDto> authorDtoList = parseResponse(mvcRes, ArrayList.class);
		//
		assertEquals(authorDtoList.size(), nbElement);
		try {
			assertNotNull(authorDtoList.get(0).getAdresse());
			assertTrue(true);
		} catch (ClassCastException c) {
			assertTrue(false);
		}
	}

	private String buildUrlEncodedFormEntity(String... params) {
		if ((params.length % 2) > 0) {
			throw new IllegalArgumentException("Need to give an even number of parameters");
		}
		StringBuilder result = new StringBuilder();
		for (int i = 0; i < params.length; i += 2) {
			if (i > 0) {
				result.append('&');
			}
			try {
				result.
						append(URLEncoder.encode(params[i], StandardCharsets.UTF_8.name())).
						append('=').
						append(URLEncoder.encode(params[i + 1], StandardCharsets.UTF_8.name()));
			} catch (UnsupportedEncodingException e) {
				throw new RuntimeException(e);
			}
		}
		return result.toString();
	}

	public static String requestBody(Object request) {
		try {
			return MAPPER.writeValueAsString(request);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}

	public static <T> T parseResponse(MvcResult result, Class<T> responseClass) {
		try {
			String contentAsString = result.getResponse().getContentAsString();
			return MAPPER.readValue(contentAsString, responseClass);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static final ObjectMapper MAPPER = new ObjectMapper()
			.configure(WRITE_DATES_AS_TIMESTAMPS, false)
			.configure(FAIL_ON_UNKNOWN_PROPERTIES, false)
			.registerModule(new JavaTimeModule());


	@Test
	public void get() {
	}
}
