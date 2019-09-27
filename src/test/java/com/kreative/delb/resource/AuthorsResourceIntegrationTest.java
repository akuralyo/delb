package com.kreative.delb.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kreative.delb.dao.AuthorDAO;
import com.kreative.delb.dao.BookDAO;
import com.kreative.delb.dao.UserDAO;
import com.kreative.delb.model.Author;
import com.kreative.delb.resource.dto.AuthorDto;
import org.apache.log4j.Logger;
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
import org.springframework.test.web.servlet.ResultMatcher;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;
import static com.kreative.delb.resource.constants.Api.*;
import static com.kreative.delb.resource.constants.Api.Resource.AUTHORS;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthorsResourceIntegrationTest {

	private static final int NB_ELEMENT = 5;

	private static final ObjectMapper MAPPER = new ObjectMapper()
			.configure(WRITE_DATES_AS_TIMESTAMPS, false)
			.configure(FAIL_ON_UNKNOWN_PROPERTIES, false)
			.registerModule(new JavaTimeModule());

	private static Logger logger = Logger.getLogger(AuthorsResourceIntegrationTest.class);

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private AuthorDAO authorDAO;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private BookDAO bookDAO;

	private Login login = new Login("ADMIN", "ADMIN");

	@Test
	public void private_findAll_ko_cuz_not_authenticated() throws Exception {
		mockMvc.perform(get(PREFIXE + PRIVATE + AUTHORS))
				.andDo(print())
				.andExpect(status().isForbidden());
	}

	@Test
	@Ignore
	public void private_findAll_ok() throws Exception {
		// Récupération du token
		MvcResult mvcResLogin = mockMvc.perform(post("/login")
				.contentType("application/x-www-form-urlencoded")
				.param("username", "ADMIN")
				.param("password", "ADMIN"))
				.andReturn();
		// mvcResLogin.getResponse().getHeader();
		//
		MvcResult mvcRes = mockMvc.perform(get(PREFIXE + PRIVATE + AUTHORS)).andReturn();
		List<AuthorDto> authorDtoList = parseResponse(mvcRes, ArrayList.class);
		//
		assertEquals(authorDtoList.size(), NB_ELEMENT);
		try {
			assertNotNull(authorDtoList.get(0).getAdresse());
			assertTrue(true);
		} catch (ClassCastException c) {
			assertTrue(false);
		}
	}

	@Test
	public void public_create_ko_cuz_not_implemented() throws Exception {
		mockMvc.perform(post(PREFIXE + PUBLIC + AUTHORS))
				.andDo(print())
				.andExpect(status().isNotImplemented());
	}

	@Test
	public void public_delete_ko_cuz_not_implemented() throws Exception {
		mockMvc.perform(delete(PREFIXE + PUBLIC + AUTHORS + "/id"))
				.andDo(print())
				.andExpect(status().isNotImplemented());
	}

	@Test
	public void public_findAll_ok() throws Exception {
		mockMvc.perform(get(PREFIXE + PUBLIC + AUTHORS))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(checkAuthor(0));
	}

	@Test
	public void public_findOne_ok() throws Exception {
		Author author = authorDAO.findAnyone();
		mockMvc.perform(get(PREFIXE + PUBLIC + AUTHORS + "/" + author.getId().toString()))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(checkAuthor(author));
	}

	@Test
	public void public_update_ko_cuz_not_implemented() throws Exception {
		mockMvc.perform(put(PREFIXE + PUBLIC + AUTHORS))
				.andDo(print())
				.andExpect(status().isNotImplemented());
	}

	@Before
	public void setUp() throws Exception {
		authorDAO.deleteAll();
		userDAO.deleteAll();
		bookDAO.deleteAll();
		//
		userDAO.createAdmin();
		authorDAO.initDb(NB_ELEMENT);
		//
		logger.debug("Nb Author : " + authorDAO.findAll().size());
		logger.debug("Nb Book : " + bookDAO.findAll().size());
	}

	private static ResultMatcher allOf(final ResultMatcher... matchers) throws Exception {
		return (result) -> {
			for (ResultMatcher m : matchers) {
				m.match(result);
			}
		};
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

	private ResultMatcher checkAuthor(int i) throws Exception {
		return allOf(
				jsonPath("$.[" + i + "].firstName", is("FirstName" + i)),
				jsonPath("$.[" + i + "].lastName", is("LastName" + i)),
				jsonPath("$.[" + i + "].nickName", is("NickName" + i)),
				jsonPath("$.[" + i + "].adresse").doesNotExist(),
				jsonPath("$.[" + i + "].bookDtoList", is(not(empty()))));
	}

	private ResultMatcher checkAuthor(Author author) throws Exception {
		return allOf(
				jsonPath("$.firstName", is(author.getFirstName())),
				jsonPath("$.lastName", is(author.getLastName())),
				jsonPath("$.nickName", is(author.getNickName())),
				jsonPath("$.adresse").doesNotExist(),
				jsonPath("$.bookDtoList", is(not(empty()))));
	}

	private static <T> T parseResponse(MvcResult result, Class<T> responseClass) {
		try {
			String contentAsString = result.getResponse().getContentAsString();
			return MAPPER.readValue(contentAsString, responseClass);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private static String requestBody(Object request) {
		try {
			return MAPPER.writeValueAsString(request);
		} catch (JsonProcessingException e) {
			throw new RuntimeException(e);
		}
	}
}
