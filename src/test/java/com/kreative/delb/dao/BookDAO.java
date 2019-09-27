package com.kreative.delb.dao;

import com.kreative.delb.model.Book;
import com.kreative.delb.objectMother.BookMother;
import com.kreative.delb.repository.BookRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookDAO {

	@Autowired
	private BookRepository bookRepository;

	public void deleteAll() {
		bookRepository.deleteAll();
	}

	public List<Book> findAll() {
		List<Book> bookList = new ArrayList<>();
		bookRepository.findAll().forEach(author -> {
			bookList.add(author);
		});
		return bookList;
	}

	public void initDb(ObjectId objectId) {
		bookRepository.saveAll(new BookMother().createBookList(objectId));
	}
}
