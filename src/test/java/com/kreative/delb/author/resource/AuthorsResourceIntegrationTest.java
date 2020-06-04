package com.kreative.delb.author.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kreative.delb.DelbApplication;
import com.kreative.delb.author.model.Author;
import com.kreative.delb.author.objectMother.AuthorMother;
import com.kreative.delb.common.resource.AbstractIntegrationtest;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.web.util.NestedServletException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.UUID;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;
import static com.kreative.delb.common.resource.constants.Api.*;
import static com.kreative.delb.common.resource.constants.Api.Resource.AUTHORS;
import static com.kreative.delb.common.resource.constants.Api.Resource.SEARCH;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertNull;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {DelbApplication.class})
public class AuthorsResourceIntegrationTest extends AbstractIntegrationtest {

	private static final int NB_ELEMENT = 5;

	private static final ObjectMapper MAPPER = new ObjectMapper()
			.configure(WRITE_DATES_AS_TIMESTAMPS, false)
			.configure(FAIL_ON_UNKNOWN_PROPERTIES, false)
			.registerModule(new JavaTimeModule());

	private static final Logger LOGGER = Logger.getLogger(AuthorsResourceIntegrationTest.class);


	@Before
	public void before() throws Exception {
		super.before();
		//
		LOGGER.debug("Nb Author : " + authorDAO.findAll().size());
		LOGGER.debug("Nb Book : " + bookDAO.findAll().size());
	}

	@Test
	@WithMockUser(username = "anonymousUser")
	public void private_create_ko() throws Exception {
		//
		mockMvc.perform(post(PREFIXE + PRIVATE + AUTHORS)
				.content(asJsonString(new AuthorMother().createAuthorDto(0)))
				.contentType(APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isNotImplemented());
	}

	@Test(expected = NestedServletException.class)
	@WithMockUser(username = "anonymousUser")
	public void private_delete_ko_cuz_object_not_existing() throws Exception {
		mockMvc.perform(delete(PREFIXE + PRIVATE + AUTHORS + "/" + UUID.randomUUID().toString())
				.contentType(APPLICATION_JSON));
	}

	@Test
	@WithMockUser(username = "anonymousUser")
	public void private_delete_ok() throws Exception {
		Author author = authorDAO.findAnyone();
		//
		mockMvc.perform(delete(PREFIXE + PRIVATE + AUTHORS + "/" + author.getIdAuthor())
				.contentType(APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
		//
		assertNull(authorDAO.findOne(author.getIdAuthor()));
	}

	@Test
	public void private_findAll_ko_cuz_not_authenticated() throws Exception {
		mockMvc.perform(get(PREFIXE + PRIVATE + AUTHORS))
				.andDo(print())
				.andExpect(status().isForbidden());
	}

	@Test
	@WithMockUser(username = "anonymousUser")
	public void private_findAll_ok() throws Exception {
		mockMvc.perform(get(PREFIXE + PRIVATE + AUTHORS))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(checkAuthorFromList(0, true))
				.andExpect(checkAuthorBook(0));
	}

	@Test
	@WithMockUser(username = "anonymousUser")
	public void private_findOne_ok() throws Exception {
		Author author = authorDAO.findAnyone();
		mockMvc.perform(get(PREFIXE + PRIVATE + AUTHORS + "/" + author.getIdAuthor()))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(checkAuthor(author, true))
				.andExpect(checkAuthorBooks(author));
	}

	@Test
	@WithMockUser(username = "anonymousUser")
	public void private_update_ok() throws Exception {
		Author author = authorDAO.findAnyone();
		//
		author.setFirstName("MyFirstName").setLastName("MyLastName").setBirthday(LocalDate.now());
		//
		String json = asJsonString(new AuthorMother().createAuthorDto(author).setIdAuthor(""));
		mockMvc.perform(put(PREFIXE + PRIVATE + AUTHORS + "/" + author.getIdAuthor())
				.content(asJsonString(new AuthorMother().createAuthorDto(author).setIdAuthor("")))
				.contentType(APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(checkAuthor(author, true));
		//
	}

	@Test
	public void public_create_ko_cuz_not_implemented() throws Exception {
		mockMvc.perform(post(PREFIXE + PUBLIC + AUTHORS)
				.content(asJsonString(new AuthorMother().createAuthorDto(0)))
				.contentType(APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isNotImplemented());
	}

	@Test
	public void public_delete_ko_cuz_not_implemented() throws Exception {
		mockMvc.perform(delete(PREFIXE + PUBLIC + AUTHORS + "/" + UUID.randomUUID().toString()))
				.andDo(print())
				.andExpect(status().isNotImplemented());
	}

	@Test
	public void public_findAll_ok() throws Exception {
		mockMvc.perform(get(PREFIXE + PUBLIC + AUTHORS))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(checkAuthorFromList(0, false))
				.andExpect(checkAuthorBook(0));
	}

	@Test(expected = NestedServletException.class)
	public void public_findOne_ko_not_found() throws Exception {
		mockMvc.perform(get(PREFIXE + PUBLIC + AUTHORS + "/" + UUID.randomUUID().toString()))
				.andDo(print())
				.andExpect(status().isNotFound());
	}

	@Test
	public void public_findOne_ok() throws Exception {
		Author author = authorDAO.findAnyone();
		mockMvc.perform(get(PREFIXE + PUBLIC + AUTHORS + "/" + author.getIdAuthor()))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(checkAuthor(author, false))
				.andExpect(checkAuthorBooks(author));
	}

	@Test
	@Ignore
	public void public_search_ok() throws Exception {
		mockMvc.perform(post(PREFIXE + PUBLIC + AUTHORS + SEARCH)
				.content(asJsonString(new AuthorMother().createMapAuthorDto(0)))
				.contentType(APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	public void public_update_ko_cuz_not_implemented() throws Exception {
		mockMvc.perform(put(PREFIXE + PUBLIC + AUTHORS + "/toto")
				.content(asJsonString(new AuthorMother().createAuthorDto(0)))
				.contentType(APPLICATION_JSON))
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
					jsonPath("$.idAuthor").exists(),
					jsonPath("$.firstName", is(author.getFirstName())),
					jsonPath("$.lastName", is(author.getLastName())),
					jsonPath("$.nickName", is(author.getNickName())),
					jsonPath("$.adresse").exists());
		} else {
			return allOf(
					jsonPath("$.idAuthor").exists(),
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
					jsonPath("$.[" + i + "].idAuthor").exists(),
					jsonPath("$.[" + i + "].firstName", is("FirstName" + i)),
					jsonPath("$.[" + i + "].lastName", is("LastName" + i)),
					jsonPath("$.[" + i + "].nickName", is("NickName" + i)),
					jsonPath("$.[" + i + "].adresse").exists(),
					jsonPath("$.[" + i + "].bookDtoList", is(not(empty()))));
		}
		return allOf(
				jsonPath("$.[" + i + "].idAuthor").exists(),
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
}
