package com.kreative.delb.domain.service.author.service;

import com.kreative.delb.domain.service.author.mapper.AuthorMapper;
import com.kreative.delb.domain.service.author.model.Author;
import com.kreative.delb.domain.service.book.mapper.BookMapper;
import com.kreative.delb.domain.spi.AuthorSpi;
import com.kreative.delb.domain.spi.BookSpi;
import com.kreative.delb.infrastructure.h2.author.model.AuthorModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorService {

  private final AuthorMapper authorMapper;

  private final AuthorSpi authorSpi;

  private final BookMapper bookMapper;

  private final BookSpi bookSpi;

  public AuthorService(
      final AuthorSpi authorSpi,
      final BookSpi bookSpi,
      final AuthorMapper authorMapper,
      final BookMapper bookMapper) {
    this.authorSpi = authorSpi;
    this.bookSpi = bookSpi;
    this.authorMapper = authorMapper;
    this.bookMapper = bookMapper;
  }

  public void deleteAuthor(final String id) {
    final AuthorModel author = authorSpi.findOneById(id);
    if (author != null) {
      authorSpi.deleteAuthor(id);
    } else {
      throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
    }
  }

  public List<Author> findAll() {
    return authorSpi.findAll().stream().map(authorMapper::mapToDto).collect(Collectors.toList());
  }

  public Author findOneById(final String id) {
    final AuthorModel authorModel = authorSpi.findOneById(id);
    if (authorModel == null) {
      throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
    } else {
      final Author author = authorMapper.mapToDto(authorModel);
      bookSpi
          .findAllByAuthorId(author.getIdAuthor())
          .forEach(book -> author.getBookList().add(bookMapper.mapToDto(book)));
      return author;
    }
  }

  public Author updateAuthorById(final String id, final Author author) {
    final AuthorModel authorUpdated = authorSpi.updateById(id, author);
    if (authorUpdated != null) {
      return authorMapper.mapToDto(authorUpdated);
    } else {
      throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
    }
  }
}
