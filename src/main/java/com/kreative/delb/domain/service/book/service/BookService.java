package com.kreative.delb.domain.service.book.service;

import com.kreative.delb.domain.service.author.mapper.AuthorMapper;
import com.kreative.delb.domain.service.book.mapper.BookMapper;
import com.kreative.delb.domain.service.book.model.Book;
import com.kreative.delb.domain.spi.AuthorSpi;
import com.kreative.delb.infrastructure.h2.author.model.AuthorModel;
import com.kreative.delb.infrastructure.h2.book.model.BookModel;
import com.kreative.delb.infrastructure.h2.book.service.BookAdapter;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookService {

  private static final Logger LOGGER = Logger.getLogger(BookService.class);

  private final AuthorSpi authorSpi;

  private final AuthorMapper authorMapper;

  private final BookAdapter bookAdapter;

  private final BookMapper bookMapper;

  public BookService(
      final AuthorSpi authorSpi,
      final AuthorMapper authorMapper,
      final BookAdapter bookAdapter,
      final BookMapper bookMapper) {
    this.authorSpi = authorSpi;
    this.authorMapper = authorMapper;
    this.bookAdapter = bookAdapter;
    this.bookMapper = bookMapper;
  }

  public Book createBook(final Book book) {
    // Vérification que l'auteur existe déjà
    final Optional<AuthorModel> authorOptional = authorSpi.findById(book.getAuthor().getIdAuthor());
    if (!authorOptional.isPresent()) {
      LOGGER.warn("Création de l'auteur");
      final AuthorModel author = authorSpi.createAuthor(book.getAuthor());
      return bookMapper.mapToDto(
          bookAdapter.createBook(createBookModelFromBook(book, author.getIdAuthor())));
    } else {
      return bookMapper.mapToDto(
          bookAdapter.createBook(createBookModelFromBook(book, book.getAuthor().getIdAuthor())));
    }
  }

  public List<Book> findAll() {

    final List<Book> bookList =
        bookAdapter.findAll().stream().map(bookMapper::mapToDto).collect(Collectors.toList());

    bookList.forEach(
        book -> {
          final AuthorModel authorModel = authorSpi.findOneById(book.getAuthor().getIdAuthor());
          book.setAuthor(authorMapper.mapToDto(authorModel));
        });

    return bookList;
  }

  private BookModel createBookModelFromBook(final Book book, final String authorId) {
    final BookModel bookModel = new BookModel();

    bookModel.setTitle(book.getTitle());
    bookModel.setAuthorId(authorId);

    return bookModel;
  }
}
