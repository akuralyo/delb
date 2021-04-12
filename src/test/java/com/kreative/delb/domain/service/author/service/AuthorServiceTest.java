package com.kreative.delb.domain.service.author.service;

import com.kreative.delb.author.objectMother.AuthorMother;
import com.kreative.delb.book.objectMother.BookMother;
import com.kreative.delb.domain.service.author.exception.AuthorNotFoundException;
import com.kreative.delb.domain.service.author.mapper.AuthorMapper;
import com.kreative.delb.domain.service.author.model.Author;
import com.kreative.delb.domain.service.book.mapper.BookMapper;
import com.kreative.delb.domain.spi.AuthorSpi;
import com.kreative.delb.domain.spi.BookSpi;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

class AuthorServiceTest {

  @Mock private AuthorSpi authorSpi;

  @Mock private AuthorMapper authorMapper;

  private AuthorService authorService;

  @Mock private BookSpi bookSpi;

  @Mock private BookMapper bookMapper;

  @Test
  public void findOneById_ok() throws AuthorNotFoundException {
    // Initiation des réponses
    when(authorSpi.findOneById(ArgumentMatchers.anyString()))
        .thenReturn(new AuthorMother().createAuthorModel(0));
    when(bookSpi.findAllByAuthorId(ArgumentMatchers.anyString()))
        .thenReturn(new BookMother().createBookList());
    // Appel
    final Author author = authorService.findOneById("id");
    // Vérification
    assertNotNull(author);
    assertEquals(author.getBookList().size(), new BookMother().createBookList().size());
  }

  @BeforeEach
  public void init() {
    initMocks(this);
    when(authorMapper.mapToDto(ArgumentMatchers.any())).thenCallRealMethod();
    when(bookMapper.mapToDto(ArgumentMatchers.any())).thenCallRealMethod();

    authorService = new AuthorService(authorSpi, bookSpi, authorMapper, bookMapper);
  }

  @Test
  public void updateAuthor_ko() {
    // Initiation des réponses
    when(authorSpi.updateById(ArgumentMatchers.anyString(), ArgumentMatchers.any(Author.class)))
        .thenReturn(null);
    // Appel
    AuthorNotFoundException exception =
            assertThrows(AuthorNotFoundException.class,
                    () ->  authorService.updateAuthorById("id", new Author()));

  }
}
