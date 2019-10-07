package com.kreative.delb.resource;

import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BooksResourceIntegrationTest extends AbstractIntegrationtest {

	private static final int NB_ELEMENT = 5;

	private static final Logger LOGGER = Logger.getLogger(AuthorsResourceIntegrationTest.class);

	@Before
	public void before() throws Exception {
		super.before();
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
