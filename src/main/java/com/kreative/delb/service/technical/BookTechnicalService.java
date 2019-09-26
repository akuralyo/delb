package com.kreative.delb.service.technical;

import com.kreative.delb.model.Book;
import com.kreative.delb.repository.BookRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookTechnicalService {

	private static Logger logger = Logger.getLogger(BookTechnicalService.class);

	@Autowired
	private BookRepository bookRepository;

	public List<Book> findAll() {
		logger.debug("Accès à la méthode");
		List<Book> bookList = new ArrayList<>();
		bookRepository.findAll().forEach(author -> {
			bookList.add(author);
		});
		logger.debug("Nb d'éléments : " + bookList.size());
		return bookList;
	}

	public List<Book> findAllByAuthorId(String authorId) {
		logger.debug("Accès à la méthode");
		List<Book> bookList = new ArrayList<>();
		bookRepository.findAllByAuthorId(authorId).forEach(author -> {
			bookList.add(author);
		});
		logger.debug("Nb d'éléments : " + bookList.size());
		return bookList;
	}
}
