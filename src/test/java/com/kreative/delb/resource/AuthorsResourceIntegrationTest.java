package com.kreative.delb.resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kreative.delb.dao.AuthorDAO;
import com.kreative.delb.dao.BookDAO;
import com.kreative.delb.dao.UserDAO;
import com.kreative.delb.model.Author;
import com.kreative.delb.objectMother.AuthorMother;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.web.util.NestedServletException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;
import static com.kreative.delb.resource.constants.Api.*;
import static com.kreative.delb.resource.constants.Api.Resource.AUTHORS;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthorsResourceIntegrationTest extends AbstractIntegrationtest {

	private static final int NB_ELEMENT = 5;

	private static final ObjectMapper MAPPER = new ObjectMapper()
			.configure(WRITE_DATES_AS_TIMESTAMPS, false)
			.configure(FAIL_ON_UNKNOWN_PROPERTIES, false)
			.registerModule(new JavaTimeModule());

	private static final Logger LOGGER = Logger.getLogger(AuthorsResourceIntegrationTest.class);

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private AuthorDAO authorDAO;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private BookDAO bookDAO;


	@Before
	public void before() throws Exception {
		super.before();
		//
		userDAO.createAdmin();
		authorDAO.initDb(NB_ELEMENT);
		//
		LOGGER.debug("Nb Author : " + authorDAO.findAll().size());
		LOGGER.debug("Nb Book : " + bookDAO.findAll().size());
	}

	@Test
	@WithMockUser(username = "admin")
	public void private_create_ok() throws Exception {
		authorDAO.deleteAll();
		//
		mockMvc.perform(post(PREFIXE + PRIVATE + AUTHORS)
				.content(asJsonString(new AuthorMother().createAuthorDto(0)))
				.contentType(APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(checkAuthor(0, true));
		//
		assertEquals(authorDAO.findAll().size(), 1);
	}

	@Test(expected = NestedServletException.class)
	@WithMockUser(username = "admin")
	public void private_delete_ko_cuz_object_not_existing() throws Exception {
		mockMvc.perform(delete(PREFIXE + PRIVATE + AUTHORS + "/" + new ObjectId().toString())
				.contentType(APPLICATION_JSON));
	}

	@Test
	@WithMockUser(username = "admin")
	public void private_delete_ok() throws Exception {
		Author author = authorDAO.findAnyone();
		//
		mockMvc.perform(delete(PREFIXE + PRIVATE + AUTHORS + "/" + author.getId().toString())
				.contentType(APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
		//
		assertNull(authorDAO.findOne(author.getId().toString()));
	}

	@Test
	public void private_findAll_ko_cuz_not_authenticated() throws Exception {
		mockMvc.perform(get(PREFIXE + PRIVATE + AUTHORS))
				.andDo(print())
				.andExpect(status().isForbidden());
	}

	@Test
	@WithMockUser(username = "admin")
	public void private_findAll_ok() throws Exception {
		mockMvc.perform(get(PREFIXE + PRIVATE + AUTHORS))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(checkAuthorFromList(0, true))
				.andExpect(checkAuthorBook(0));
	}

	@Test
	@WithMockUser(username = "admin")
	public void private_update_ok() throws Exception {
		Author author = authorDAO.findAnyone();
		//
		author.setFirstName("MyFirstName").setLastName("MyLastName");
		//
		mockMvc.perform(put(PREFIXE + PRIVATE + AUTHORS + "/" + author.getId().toString())
				.content(asJsonString(new AuthorMother().createAuthorDto(author)))
				.contentType(APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(checkAuthor(author, true));
		//
	}

	@Test
	public void public_create_ko_cuz_not_implemented() throws Exception {
		mockMvc.perform(post(PREFIXE + PUBLIC + AUTHORS))
				.andDo(print())
				.andExpect(status().isNotImplemented());
	}

	@Test
	public void public_delete_ko_cuz_not_implemented() throws Exception {
		mockMvc.perform(delete(PREFIXE + PUBLIC + AUTHORS + "/" + new ObjectId().toString()))
				.andDo(print())
				.andExpect(status().isNotImplemented());
	}

	@Test
	public void public_findAll_ok() throws Exception {
		mockMvc.perform(get(PREFIXE + PUBLIC + AUTHORS))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(checkAuthorFromList(0, false))
				.andExpect(checkAuthorBook(1));
	}

	@Test(expected = NestedServletException.class)
	public void public_findOne_ko_not_found() throws Exception {
		mockMvc.perform(get(PREFIXE + PUBLIC + AUTHORS + "/" + new ObjectId().toString()))
				.andDo(print())
				.andExpect(status().isNotFound());
	}

	@Test
	public void public_findOne_ok() throws Exception {
		Author author = authorDAO.findAnyone();
		mockMvc.perform(get(PREFIXE + PUBLIC + AUTHORS + "/" + author.getId().toString()))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(checkAuthor(author, false))
				.andExpect(checkAuthorBooks(author));
	}

	@Test
	public void public_update_ko_cuz_not_implemented() throws Exception {
		mockMvc.perform(put(PREFIXE + PUBLIC + AUTHORS))
				.andDo(print())
				.andExpect(status().isNotImplemented());
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

	private ResultMatcher checkAuthor(int i, boolean apiPrivate) {
		return checkAuthor(new AuthorMother().createAuthor(i), apiPrivate);
	}

	private ResultMatcher checkAuthor(Author author, boolean apiPrivate) {
		if (apiPrivate) {
			return allOf(
					jsonPath("$.id").exists(),
					jsonPath("$.firstName", is(author.getFirstName())),
					jsonPath("$.lastName", is(author.getLastName())),
					jsonPath("$.nickName", is(author.getNickName())),
					jsonPath("$.adresse").exists());
		} else {
			return allOf(
					jsonPath("$.id").exists(),
					jsonPath("$.firstName", is(author.getFirstName())),
					jsonPath("$.lastName", is(author.getLastName())),
					jsonPath("$.nickName", is(author.getNickName())),
					jsonPath("$.adresse").doesNotExist());
		}
	}

	private ResultMatcher checkAuthorBook(int indexAuthor) {
		return allOf(
				jsonPath("$.[" + indexAuthor + "].bookDtoList", is(not(empty()))));
	}

	private ResultMatcher checkAuthorBooks(Author author) {
		return allOf(
				jsonPath("$.bookDtoList", is(not(empty()))));
	}

	private ResultMatcher checkAuthorFromList(int i, boolean apiPrivate) {
		if (apiPrivate) {
			return allOf(
					jsonPath("$.[" + i + "].id").exists(),
					jsonPath("$.[" + i + "].firstName", is("FirstName" + i)),
					jsonPath("$.[" + i + "].lastName", is("LastName" + i)),
					jsonPath("$.[" + i + "].nickName", is("NickName" + i)),
					jsonPath("$.[" + i + "].adresse").exists(),
					jsonPath("$.[" + i + "].bookDtoList", is(not(empty()))));
		}
		return allOf(
				jsonPath("$.[" + i + "].id").exists(),
				jsonPath("$.[" + i + "].firstName", is("FirstName" + i)),
				jsonPath("$.[" + i + "].lastName", is("LastName" + i)),
				jsonPath("$.[" + i + "].nickName", is("NickName" + i)),
				jsonPath("$.[" + i + "].adresse").doesNotExist(),
				jsonPath("$.[" + i + "].bookDtoList", is(not(empty()))));
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
