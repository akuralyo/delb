package com.kreative.delb.infrastructure.h2.book.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.kreative.delb.infrastructure.h2.book.model.BookModel;

public interface BookRepository extends CrudRepository<BookModel, String> {

  List<BookModel> findAllByAuthorId(String authorId);
}
