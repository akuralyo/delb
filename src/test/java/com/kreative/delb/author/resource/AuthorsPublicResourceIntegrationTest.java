package com.kreative.delb.author.resource;

import com.kreative.delb.DelbApplication;
import com.kreative.delb.author.objectMother.AuthorMother;
import com.kreative.delb.infrastructure.h2.author.model.AuthorModel;
import org.apache.log4j.Logger;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.NestedServletException;

import java.util.UUID;

import static com.kreative.delb.application.common.resource.constants.Api.PREFIXE;
import static com.kreative.delb.application.common.resource.constants.Api.PUBLIC;
import static com.kreative.delb.application.common.resource.constants.Api.Resource.AUTHORS;
import static com.kreative.delb.application.common.resource.constants.Api.Resource.SEARCH;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {DelbApplication.class})
public class AuthorsPublicResourceIntegrationTest extends AbstractAuthorResourceIntegrationTest {

  private static final Logger LOGGER = Logger.getLogger(AuthorsPublicResourceIntegrationTest.class);

  @Test
  public void public_create_ko_cuz_forbidden() throws Exception {
    mockMvc
        .perform(
            post(PREFIXE + PUBLIC + AUTHORS)
                .content(asJsonString(new AuthorMother().createAuthorDto(0)))
                .contentType(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isForbidden());
  }

  @Test
  public void public_delete_ko_cuz_forbidden() throws Exception {
    mockMvc
        .perform(delete(PREFIXE + PUBLIC + AUTHORS + "/" + UUID.randomUUID().toString()))
        .andDo(print())
        .andExpect(status().isForbidden());
  }

  @Test
  public void public_findAll_ok() throws Exception {
    mockMvc
        .perform(get(PREFIXE + PUBLIC + AUTHORS))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(checkAuthorFromList(0, false))
        .andExpect(checkAuthorBook(0));
  }

  @Test
  public void public_findOneByIdAndFilterApply_ok() throws Exception {
    AuthorModel author = authorDAO.findAnyone();
    mockMvc
        .perform(
            get(PREFIXE + PUBLIC + AUTHORS + "/" + author.getIdAuthor())
                .param("filterList", "firstName", "lastName"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(checkAuthorFiltered(author));
  }

  @Test
  public void public_findOne_ko_not_found() throws Exception {
    mockMvc
        .perform(get(PREFIXE + PUBLIC + AUTHORS + "/" + UUID.randomUUID().toString()))
        .andDo(print())
        .andExpect(status().isNotFound());
  }

  @Test
  public void public_findOne_ok() throws Exception {
    AuthorModel author = authorDAO.findAnyone();
    mockMvc
        .perform(get(PREFIXE + PUBLIC + AUTHORS + "/" + author.getIdAuthor()))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(checkAuthor(author, false))
        .andExpect(checkAuthorBooks(author));
  }

  @Test
  @Ignore
  public void public_search_ok() throws Exception {
    mockMvc
        .perform(
            post(PREFIXE + PUBLIC + AUTHORS + SEARCH)
                .content(asJsonString(new AuthorMother().createMapAuthorDto(0)))
                .contentType(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
  }

  @Test
  public void public_update_ko_cuz_forbidden() throws Exception {
    mockMvc
        .perform(
            put(PREFIXE + PUBLIC + AUTHORS + "/toto")
                .content(asJsonString(new AuthorMother().createAuthorDto(0)))
                .contentType(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isForbidden());
  }
}
