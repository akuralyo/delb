package com.kreative.delb.common.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kreative.delb.author.dao.AuthorDAO;
import com.kreative.delb.book.dao.BookDAO;
import com.kreative.delb.user.dao.UserDAO;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

public abstract class AbstractIntegrationtest {

	@Autowired
	protected AuthorDAO authorDAO;

	@Autowired
	protected UserDAO userDAO;

	@Autowired
	protected BookDAO bookDAO;

	@Autowired
	protected MockMvc mockMvc;

	@Before
	protected void before() throws Exception {
	}

	public static ResultMatcher allOf(final ResultMatcher... matchers) {
		return (result) -> {
			for (ResultMatcher m : matchers) {
				m.match(result);
			}
		};
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
