package com.kreative.delb.domain.service.author.service;

import com.kreative.delb.application.author.dto.AuthorDto;
import com.kreative.delb.author.objectMother.AuthorMother;
import com.kreative.delb.book.objectMother.BookMother;
import com.kreative.delb.domain.service.author.mapper.AuthorMapper;
import com.kreative.delb.domain.service.author.model.Author;
import com.kreative.delb.domain.service.book.mapper.BookMapper;
import com.kreative.delb.infrastructure.h2.author.service.AuthorAdapter;
import com.kreative.delb.infrastructure.h2.book.service.BookAdapter;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class AuthorServiceTest {

  @Mock private AuthorAdapter authorAdapter;

  @Mock private AuthorMapper authorMapper;

  @InjectMocks private AuthorService authorService;

  @Mock private BookAdapter bookAdapter;

  @Mock private BookMapper bookMapper;

  @Test
  public void findOneById_ok() {
    // Initiation des réponses
    when(authorAdapter.findOneById(ArgumentMatchers.anyString()))
        .thenReturn(new AuthorMother().createAuthor(0));
    when(bookAdapter.findAllByAuthorId(ArgumentMatchers.anyString()))
        .thenReturn(new BookMother().createBookList());
    // Appel
    final Author author = authorService.findOneById("id");
    // Vérification
    assertNotNull(author);
    assertEquals(author.getBookList().size(), new BookMother().createBookList().size());
  }

  @Before
  public void setUp() {
    initMocks(this);
    when(authorMapper.mapToDto(ArgumentMatchers.any())).thenCallRealMethod();
    when(bookMapper.mapToDto(ArgumentMatchers.any())).thenCallRealMethod();
  }

  @Test(expected = HttpClientErrorException.class)
  public void updateAuthor_ko() {
    // Initiation des réponses
    when(authorAdapter.updateById(ArgumentMatchers.anyString(), ArgumentMatchers.any()))
        .thenReturn(null);
    // Appel
    authorService.updateAuthorById("id", new AuthorDto());
  }
}
