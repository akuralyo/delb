package com.kreative.delb.book.dao;

import com.kreative.delb.book.model.Book;
import com.kreative.delb.book.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class BookDAO {

	@Autowired
	private BookRepository bookRepository;

	public List<Book> findAll() {
		List<Book> bookList = new ArrayList<>();
		bookRepository.findAll().forEach(bookList::add);
		return bookList;
	}

}
