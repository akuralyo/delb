package com.kreative.delb.book.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kreative.delb.infrastructure.h2.book.model.BookModel;
import com.kreative.delb.infrastructure.h2.book.repository.BookRepository;

@Component
public class BookDAO {

  @Autowired private BookRepository bookRepository;

  public List<BookModel> findAll() {
    List<BookModel> bookList = new ArrayList<>();
    bookRepository.findAll().forEach(bookList::add);
    return bookList;
  }
}
