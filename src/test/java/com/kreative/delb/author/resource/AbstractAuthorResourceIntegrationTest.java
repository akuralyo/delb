package com.kreative.delb.author.resource;

import com.kreative.delb.author.objectMother.AuthorMother;
import com.kreative.delb.common.resource.AbstractIntegrationTest;
import com.kreative.delb.infrastructure.h2.author.model.AuthorModel;
import org.springframework.test.web.servlet.ResultMatcher;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

public abstract class AbstractAuthorResourceIntegrationTest extends AbstractIntegrationTest {

  protected ResultMatcher checkAuthor(AuthorModel author, boolean apiPrivate) {
    if (apiPrivate) {
      return allOf(
          jsonPath("$.idAuthor").exists(),
          jsonPath("$.firstName", is(author.getFirstName())),
          jsonPath("$.lastName", is(author.getLastName())),
          jsonPath("$.nickName", is(author.getNickName())),
          jsonPath("$.adresse").exists());
    } else {
      return allOf(
          jsonPath("$.idAuthor").exists(),
          jsonPath("$.firstName", is(author.getFirstName())),
          jsonPath("$.lastName", is(author.getLastName())),
          jsonPath("$.nickName", is(author.getNickName())),
          jsonPath("$.adresse").doesNotExist());
    }
  }

  protected ResultMatcher checkAuthor(int i, boolean apiPrivate) {
    return checkAuthor(new AuthorMother().createAuthor(i), apiPrivate);
  }

  protected ResultMatcher checkAuthorBook(int indexAuthor) {
    return allOf(jsonPath("$.[" + indexAuthor + "].bookDtoList", is(not(empty()))));
  }

  protected ResultMatcher checkAuthorBooks(AuthorModel author) {
    return allOf(jsonPath("$.bookDtoList", is(not(empty()))));
  }

  protected ResultMatcher checkAuthorFiltered(AuthorModel author) {
    return allOf(
        jsonPath("$.idAuthor").doesNotExist(),
        jsonPath("$.firstName", is(author.getFirstName())),
        jsonPath("$.lastName", is(author.getLastName())),
        jsonPath("$.nickName").doesNotExist(),
        jsonPath("$.adresse").doesNotExist());
  }

  protected ResultMatcher checkAuthorFromList(int i, boolean apiPrivate) {
    if (apiPrivate) {
      return allOf(
          jsonPath("$.[" + i + "].idAuthor").exists(),
          jsonPath("$.[" + i + "].firstName", is("FirstName" + i)),
          jsonPath("$.[" + i + "].lastName", is("LastName" + i)),
          jsonPath("$.[" + i + "].nickName", is("NickName" + i)),
          jsonPath("$.[" + i + "].adresse").exists(),
          jsonPath("$.[" + i + "].bookDtoList", is(not(empty()))));
    }
    return allOf(
        jsonPath("$.[" + i + "].idAuthor").exists(),
        jsonPath("$.[" + i + "].firstName", is("FirstName" + i)),
        jsonPath("$.[" + i + "].lastName", is("LastName" + i)),
        jsonPath("$.[" + i + "].nickName", is("NickName" + i)),
        jsonPath("$.[" + i + "].adresse").doesNotExist(),
        jsonPath("$.[" + i + "].bookDtoList", is(not(empty()))));
  }
}
