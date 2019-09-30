package com.kreative.delb.resource;

import com.kreative.delb.dao.AuthorDAO;
import com.kreative.delb.dao.BookDAO;
import com.kreative.delb.dao.UserDAO;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BooksResourceIntegrationTest extends AbstractIntegrationtest {

	private static final int NB_ELEMENT = 5;

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
	public void private_create_ok_author_existing() {
	}

	@Test
	@WithMockUser(username = "admin")
	public void private_create_ok_author_not_existing() {
	}
}
