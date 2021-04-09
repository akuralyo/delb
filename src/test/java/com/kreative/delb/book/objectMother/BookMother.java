package com.kreative.delb.book.objectMother;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.kreative.delb.infrastructure.h2.book.model.BookModel;

public class BookMother {

  public BookModel createBook(final int i, final String authorId) {
    final BookModel bookModel = new BookModel();

    bookModel.setIdBook(UUID.randomUUID().toString());
    bookModel.setTitle("Le livre numéro" + i + " de " + authorId);
    bookModel.setAuthorId(authorId);

    return bookModel;
  }

  public List<BookModel> createBookList() {
    final List<BookModel> bookList = new ArrayList<>();
    bookList.add(createBook(0, UUID.randomUUID().toString()));
    bookList.add(createBook(1, UUID.randomUUID().toString()));
    bookList.add(createBook(2, UUID.randomUUID().toString()));
    return bookList;
  }

  public List<BookModel> createBookList(final String authorId) {
    final List<BookModel> bookList = new ArrayList<>();
    bookList.add(createBook(0, authorId));
    bookList.add(createBook(1, authorId));
    bookList.add(createBook(2, authorId));
    return bookList;
  }
}
