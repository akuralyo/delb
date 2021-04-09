package com.kreative.delb.book.resource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;

import com.kreative.delb.common.resource.AbstractIntegrationTest;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BooksPrivateResourceIntegrationTest extends AbstractIntegrationTest {

  private static final Logger LOGGER = Logger.getLogger(BooksPrivateResourceIntegrationTest.class);

  @Test
  @WithMockUser(username = "admin")
  public void private_create_ok_author_existing() {}

  @Test
  @WithMockUser(username = "admin")
  public void private_create_ok_author_not_existing() {}
}
