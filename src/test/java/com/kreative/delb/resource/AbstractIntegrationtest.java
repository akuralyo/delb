package com.kreative.delb.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kreative.delb.dao.AuthorDAO;
import com.kreative.delb.dao.BookDAO;
import com.kreative.delb.dao.UserDAO;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.ResultMatcher;

public abstract class AbstractIntegrationtest {


	@Autowired
	private AuthorDAO authorDAO;

	@Autowired
	private UserDAO userDAO;

	@Autowired
	private BookDAO bookDAO;

	@Before
	public void before() throws Exception {
		authorDAO.deleteAll();
		userDAO.deleteAll();
		bookDAO.deleteAll();
	}

	protected static ResultMatcher allOf(final ResultMatcher... matchers) {
		return (result) -> {
			for (ResultMatcher m : matchers) {
				m.match(result);
			}
		};
	}

	protected static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
