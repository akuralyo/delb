package com.kreative.delb.repository;

import com.kreative.delb.model.Book;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, String> {

	List<Book> findAllByAuthorId(String authorId);

}