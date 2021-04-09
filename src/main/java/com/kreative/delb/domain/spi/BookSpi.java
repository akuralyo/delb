package com.kreative.delb.domain.spi;

import java.util.List;

import com.kreative.delb.infrastructure.h2.book.model.BookModel;

public interface BookSpi {

  BookModel createBook(BookModel author);

  List<BookModel> findAll();

  List<BookModel> findAllByAuthorId(String authorId);
}
