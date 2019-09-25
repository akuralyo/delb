package com.kreative.delb.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kreative.delb.dao.AuthorDAO;
import com.kreative.delb.resource.author.AuthorsPublicResource;
import com.kreative.delb.resource.constants.Api;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class AuthorsPublicResourceTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private AuthorDAO authorDAO;

	@Before
	public void setUp() throws Exception {
		authorDAO.deleteAll();
	}

	@Test
	public void findAll_public() throws Exception {
		authorDAO.initDb(5);
		MvcResult mvcRes =  mockMvc.perform(MockMvcRequestBuilders.get(Api.API + Api.PUBLIC + Api.AUTHORS)).andReturn();
	}

	@Test
	public void get() {
	}
}
