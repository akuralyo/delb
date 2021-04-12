package com.kreative.delb.domain.service.author.mapper;

import com.kreative.delb.author.objectMother.AuthorMother;
import com.kreative.delb.domain.service.author.model.Author;
import com.kreative.delb.infrastructure.h2.author.model.AuthorModel;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AuthorMapperTest {

  @Test
  public void mapToDto() {
    final AuthorModel authorModel = new AuthorMother().createAuthorModel(0);
    final Author author = new AuthorMapper().mapToDto(authorModel);
    //
    assertNotNull(author);
    assertEquals(authorModel.getIdAuthor(), author.getIdAuthor());
    assertEquals(authorModel.getFirstName(), author.getFirstName());
    assertEquals(authorModel.getLastName(), author.getLastName());
    assertEquals(authorModel.getNickName(), author.getNickName());
    assertEquals(authorModel.getBirthday(), author.getBirthday());
  }
}
