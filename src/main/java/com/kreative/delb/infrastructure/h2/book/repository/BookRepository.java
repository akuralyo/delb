package com.kreative.delb.infrastructure.h2.book.repository;

import com.kreative.delb.infrastructure.h2.book.model.BookModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<BookModel, String> {

  List<BookModel> findAllByAuthorId(String authorId);
}
