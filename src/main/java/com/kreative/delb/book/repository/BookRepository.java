package com.kreative.delb.book.repository;

import com.kreative.delb.book.model.Book;
import org.bson.types.ObjectId;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, String> {

	List<Book> findAllByAuthorId(ObjectId authorId);
}
