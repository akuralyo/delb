package com.kreative.delb.service.technical;

import com.kreative.delb.model.Book;
import com.kreative.delb.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookTechnicalService {

	@Autowired
	private BookRepository bookRepository;

	public List<Book> findAll() {
		List<Book> bookList = new ArrayList<>();
		bookRepository.findAll().forEach(author -> {
			bookList.add(author);
		});
		return bookList;
	}

	public List<Book> findAllByAuthorId(String authorId) {
		List<Book> bookList = new ArrayList<>();
		bookRepository.findAllByAuthorId(authorId).forEach(author -> {
			bookList.add(author);
		});
		return bookList;
	}
}
