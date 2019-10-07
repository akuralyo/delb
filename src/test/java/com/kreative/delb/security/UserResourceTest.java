package com.kreative.delb.security;

import com.kreative.delb.resource.AbstractIntegrationtest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserResourceTest extends AbstractIntegrationtest {

	@Before
	public void before() throws Exception {
		super.before();
	}

	@Test
	public void createUser() {
	}

	@Test
	public void getSelf() {
	}

	@Test
	public void update() {
	}
}
