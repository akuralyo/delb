package com.kreative.delb.domain.spi;

import com.kreative.delb.infrastructure.h2.book.model.BookModel;

import java.util.List;

public interface BookSpi {

  BookModel createBook(BookModel author);

  List<BookModel> findAll();

  List<BookModel> findAllByAuthorId(String authorId);
}
