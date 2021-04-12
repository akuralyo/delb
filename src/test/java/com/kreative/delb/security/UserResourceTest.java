package com.kreative.delb.security;

import com.kreative.delb.common.resource.AbstractIntegrationTest;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserResourceTest extends AbstractIntegrationTest {

  @Override
  @Before
  public void before() throws Exception {
    super.before();
  }

  @Test
  public void createUser() {}

  @Test
  public void getSelf() {}

  @Test
  public void update() {}
}
