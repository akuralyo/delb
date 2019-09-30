package com.kreative.delb.service.technical;

import com.kreative.delb.model.Book;
import com.kreative.delb.repository.BookRepository;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookTechnicalService {

	private static final Logger LOGGER = Logger.getLogger(BookTechnicalService.class);

	@Autowired
	private BookRepository bookRepository;

	public Book createBook(Book author) {
		return bookRepository.save(author);
	}

	public List<Book> findAll() {
		LOGGER.debug("Accès à la méthode");
		List<Book> bookList = new ArrayList<>();
		bookRepository.findAll().forEach(bookList::add);
		LOGGER.debug("Nb d'éléments : " + bookList.size());
		return bookList;
	}

	public List<Book> findAllByAuthorId(String authorId) {
		LOGGER.debug("Accès à la méthode");
		List<Book> bookList = new ArrayList<>(bookRepository.findAllByAuthorId(new ObjectId(authorId)));
		LOGGER.debug("Nb d'éléments : " + bookList.size());
		return bookList;
	}
}
