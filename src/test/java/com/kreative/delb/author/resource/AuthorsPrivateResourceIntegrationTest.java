package com.kreative.delb.author.resource;

import com.kreative.delb.DelbApplication;
import com.kreative.delb.application.author.dto.AuthorDto;
import com.kreative.delb.author.objectMother.AuthorMother;
import com.kreative.delb.infrastructure.h2.author.model.AuthorModel;
import org.apache.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.util.NestedServletException;

import java.time.LocalDate;
import java.util.UUID;

import static com.kreative.delb.application.common.resource.constants.Api.PREFIXE;
import static com.kreative.delb.application.common.resource.constants.Api.PRIVATE;
import static com.kreative.delb.application.common.resource.constants.Api.Resource.AUTHORS;
import static org.junit.Assert.assertNull;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {DelbApplication.class})
public class AuthorsPrivateResourceIntegrationTest extends AbstractAuthorResourceIntegrationTest {

  private static final Logger LOGGER =
      Logger.getLogger(AuthorsPrivateResourceIntegrationTest.class);

  @Test
  @WithMockUser(username = "anonymousUser")
  public void private_create_ko() throws Exception {
    //
    mockMvc
        .perform(
            post(PREFIXE + PRIVATE + AUTHORS)
                .content(asJsonString(new AuthorMother().createAuthorDto(0)))
                .contentType(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isNotImplemented());
  }

  @Test
  @WithMockUser(username = "anonymousUser")
  public void private_delete_ko_cuz_object_not_existing() throws Exception {
    mockMvc.perform(
        delete(PREFIXE + PRIVATE + AUTHORS + "/" + UUID.randomUUID().toString())
            .contentType(APPLICATION_JSON));
  }

  @Test
  @WithMockUser(username = "anonymousUser")
  public void private_delete_ok() throws Exception {
    final AuthorModel author = authorDAO.findAnyone();
    //
    mockMvc
        .perform(
            delete(PREFIXE + PRIVATE + AUTHORS + "/" + author.getIdAuthor())
                .contentType(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk());
    //
    assertNull(authorDAO.findOne(author.getIdAuthor()));
  }

  @Test
  public void private_findAll_ko_cuz_not_authenticated() throws Exception {
    mockMvc
        .perform(get(PREFIXE + PRIVATE + AUTHORS))
        .andDo(print())
        .andExpect(status().isForbidden());
  }

  @Test
  @WithMockUser(username = "anonymousUser")
  public void private_findAll_ok() throws Exception {
    mockMvc
        .perform(get(PREFIXE + PRIVATE + AUTHORS))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(checkAuthorFromList(0, true))
        .andExpect(checkAuthorBook(0));
  }

  @Test
  @WithMockUser(username = "anonymousUser")
  public void private_findOne_ok() throws Exception {
    final AuthorModel author = authorDAO.findAnyone();
    mockMvc
        .perform(get(PREFIXE + PRIVATE + AUTHORS + "/" + author.getIdAuthor()))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(checkAuthor(author, true))
        .andExpect(checkAuthorBooks(author));
  }

  @Test
  @WithMockUser(
      username = "anonymousUser",
      authorities = {"READER"})
  public void private_update_ko_cuz_wrong_authorities() throws Exception {
    final AuthorModel authorModel = authorDAO.findAnyone();
    //
    authorModel.setFirstName("MyFirstName");
    authorModel.setLastName("MyLastName");
    authorModel.setBirthday(LocalDate.now());

    final AuthorDto authorDto = new AuthorMother().createAuthorDto(authorModel);
    authorDto.setIdAuthor("");
    //
    mockMvc
        .perform(
            put(PREFIXE + PRIVATE + AUTHORS + "/" + authorModel.getIdAuthor())
                .content(asJsonString(authorDto))
                .contentType(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(checkAuthor(authorModel, true));
    //
  }

  @Test
  @WithMockUser(
      username = "anonymousUser",
      authorities = {"MODO"})
  public void private_update_ok_cuz_right_authoriies() throws Exception {
    final AuthorModel author = authorDAO.findAnyone();
    //
    author.setFirstName("MyFirstName");
    author.setLastName("MyLastName");
    author.setBirthday(LocalDate.now());

    final AuthorDto authorDto = new AuthorMother().createAuthorDto(author);
    authorDto.setIdAuthor("");

    //
    mockMvc
        .perform(
            put(PREFIXE + PRIVATE + AUTHORS + "/" + author.getIdAuthor())
                .content(asJsonString(authorDto))
                .contentType(APPLICATION_JSON))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(checkAuthor(author, true));
    //
  }
}
