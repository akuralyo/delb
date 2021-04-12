package com.kreative.delb.infrastructure.h2.author.adapter;

import com.kreative.delb.author.objectMother.AuthorMother;
import com.kreative.delb.domain.service.author.model.Author;
import com.kreative.delb.infrastructure.h2.author.model.AuthorModel;
import com.kreative.delb.infrastructure.h2.author.repository.AuthorRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class AuthorAdapterTest {

  private AuthorAdapter authorAdapter;

  @Mock private AuthorRepository authorRepository;

  @Test
  public void findAll() {
    // Initiation des réponses
    when(authorRepository.findAll()).thenReturn(new AuthorMother().createAuthorList());
    // Appel
    final List<AuthorModel> authorList = authorAdapter.findAll();
    // Vérification
    assertEquals(new AuthorMother().createAuthorList().size(), authorList.size());
    checkObject_author(authorList.get(1), new AuthorMother().createAuthorModel(1));
  }

  @Test
  public void findOneById_ko() {
    // Initiation des réponses
    when(authorRepository.findOneByIdAuthor(ArgumentMatchers.anyString()))
        .thenReturn(Optional.empty());
    //
    final AuthorModel author = authorAdapter.findOneById("id");
    // Vérification
    Assert.assertNull(author);
  }

  @Test
  public void findOneById_ok() {
    // Initiation des réponses
    final String idAuthor = UUID.randomUUID().toString();
    when(authorRepository.findOneByIdAuthor(ArgumentMatchers.anyString()))
        .thenReturn(Optional.of(new AuthorMother().createAuthorModel(0, idAuthor)));
    // Appel
    final AuthorModel author = authorAdapter.findOneById("id");
    // Vérification
    checkObject_author(author, new AuthorMother().createAuthorModel(0, idAuthor));
  }

  @BeforeEach
  public void setUp() {
    initMocks(this);
    authorAdapter = new AuthorAdapter(authorRepository);
  }

  @Test
  public void update_ko() {
    when(authorRepository.findById(ArgumentMatchers.anyString())).thenReturn(Optional.empty());
    //
    final AuthorModel author = authorAdapter.updateById("id", new Author());
    //
    Assert.assertNull(author);
  }

  @Test
  public void update_ok() {
    when(authorRepository.findById(ArgumentMatchers.anyString()))
        .thenReturn(Optional.of(new AuthorMother().createAuthorModel(0)));
    final AuthorModel authorModel = new AuthorMother().createAuthorModel(0);
    authorModel.setFirstName("MyFirstName");

    final Author author = new AuthorMother().createAuthor(0);
    author.setFirstName("MyFirstName");

    when(authorRepository.save(ArgumentMatchers.any())).thenReturn(authorModel);
    //
    final AuthorModel actualResponse = authorAdapter.updateById(authorModel.getIdAuthor(), author);
    //
    Assert.assertEquals(authorModel, actualResponse);
  }

  private void checkObject_author(final AuthorModel expected, final AuthorModel returned) {
    assertNotNull(returned);
    assertNotNull(returned.getIdAuthor());
    assertEquals(returned.getFirstName(), expected.getFirstName());
    assertEquals(returned.getLastName(), expected.getLastName());
    assertEquals(returned.getNickName(), expected.getNickName());
    assertEquals(returned.getBirthday(), expected.getBirthday());
  }
}
