package com.kreative.delb.infrastructure.h2.book.service;

import com.kreative.delb.domain.spi.BookSpi;
import com.kreative.delb.infrastructure.h2.book.model.BookModel;
import com.kreative.delb.infrastructure.h2.book.repository.BookRepository;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

public class BookAdapter implements BookSpi {

  private static final Logger LOGGER = Logger.getLogger(BookAdapter.class);

  private BookRepository bookRepository;

  public BookAdapter(BookRepository bookRepository) {
    this.bookRepository = bookRepository;
  }

  @Override
  public BookModel createBook(BookModel author) {
    return bookRepository.save(author);
  }

  @Override
  public List<BookModel> findAll() {
    LOGGER.debug("Accès à la méthode");
    List<BookModel> bookList = new ArrayList<>();
    bookRepository.findAll().forEach(bookList::add);
    LOGGER.debug("Nb d'éléments : " + bookList.size());
    return bookList;
  }

  @Override
  public List<BookModel> findAllByAuthorId(String authorId) {
    LOGGER.debug("Accès à la méthode");
    List<BookModel> bookList = new ArrayList<>(bookRepository.findAllByAuthorId(authorId));
    LOGGER.debug("Nb d'éléments : " + bookList.size());
    return bookList;
  }
}
