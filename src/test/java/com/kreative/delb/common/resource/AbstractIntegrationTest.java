package com.kreative.delb.common.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kreative.delb.application.security.ConstantesRole;
import com.kreative.delb.infrastructure.h2.author.dao.AuthorDAO;
import com.kreative.delb.author.objectMother.AuthorMother;
import com.kreative.delb.book.dao.BookDAO;
import com.kreative.delb.book.objectMother.BookMother;
import com.kreative.delb.infrastructure.h2.User.model.UserModel;
import com.kreative.delb.infrastructure.h2.User.repository.UserRepository;
import com.kreative.delb.infrastructure.h2.author.model.AuthorModel;
import com.kreative.delb.infrastructure.h2.author.repository.AuthorRepository;
import com.kreative.delb.infrastructure.h2.book.repository.BookRepository;
import com.kreative.delb.user.dao.UserDAO;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import java.io.IOException;
import java.util.UUID;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

public abstract class AbstractIntegrationTest {

  private static final Logger LOGGER = Logger.getLogger(AbstractIntegrationTest.class);

  private static final ObjectMapper MAPPER =
      new ObjectMapper()
          .configure(WRITE_DATES_AS_TIMESTAMPS, false)
          .configure(FAIL_ON_UNKNOWN_PROPERTIES, false)
          .registerModule(new JavaTimeModule());

  @Autowired protected AuthorDAO authorDAO;

  @Autowired protected BookDAO bookDAO;

  @Autowired protected UserDAO userDAO;

  @Autowired protected MockMvc mockMvc;

  @Autowired private AuthorRepository authorRepository;

  @Autowired private BookRepository bookRepository;

  @Autowired private UserRepository userRepository;

  @Before
  public void before() throws Exception {
    authorRepository.deleteAll();
    bookRepository.deleteAll();
    //
    initDbAuthor(5);
    //
    LOGGER.debug("Nb Author : " + authorDAO.findAll().size());
    LOGGER.debug("Nb Book : " + bookDAO.findAll().size());
  }

  private void createAuthor(final int i) {
    UserModel user = createUserModel(i, ConstantesRole.ROLE_AUTHOR.name());
    final AuthorModel author =
        authorRepository.save(new AuthorMother().createAuthorModel(i, user.getId()));
    bookRepository.saveAll(new BookMother().createBookList(author.getIdAuthor()));
  }

  private UserModel createUserModel(int i, String roleLabel) {
    String idMember = UUID.randomUUID().toString();
    UserModel member = new UserModel();
    member.setId(idMember);
    member.setUsername("Username " + roleLabel + " N°" + i);
    member.setPassword("Password " + roleLabel + " N°" + i);
    member.setPassword("");
    /**
     * Role role1 = new Role() .setId(UUID.randomUUID().toString()) .setMember(member)
     * .setRoleName(roleLabel);
     *
     * <p>member.getRoles().add(role1);
     */
    return userRepository.save(member);
  }

  private void initDbAuthor(final int max) {
    for (int i = 0; i < max; i++) {
      createAuthor(i);
    }
  }

  protected static ResultMatcher allOf(final ResultMatcher... matchers) {
    return (result) -> {
      for (final ResultMatcher m : matchers) {
        m.match(result);
      }
    };
  }

  protected static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (final Exception e) {
      throw new RuntimeException(e);
    }
  }

  protected static <T> T parseResponse(final MvcResult result, final Class<T> responseClass) {
    try {
      final String contentAsString = result.getResponse().getContentAsString();
      return MAPPER.readValue(contentAsString, responseClass);
    } catch (final IOException e) {
      throw new RuntimeException(e);
    }
  }
}
