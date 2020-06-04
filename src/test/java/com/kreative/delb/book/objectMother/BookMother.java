package com.kreative.delb.book.objectMother;

import com.kreative.delb.book.model.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class BookMother {

	public Book createBook(int i, String authorId) {
		return new Book()
				.setIdBook(UUID.randomUUID().toString())
				.setTitle("Mon livre N°" + i + " de " + authorId)
				.setAuthorId(authorId);
	}

	public List<Book> createBookList(String authorId) {
		List<Book> bookList = new ArrayList<>();
		bookList.add(createBook(0, authorId));
		bookList.add(createBook(1, authorId));
		bookList.add(createBook(2, authorId));
		return bookList;
	}

	public List<Book> createBookList() {
		List<Book> bookList = new ArrayList<>();
		bookList.add(createBook(0, UUID.randomUUID().toString()));
		bookList.add(createBook(1, UUID.randomUUID().toString()));
		bookList.add(createBook(2, UUID.randomUUID().toString()));
		return bookList;
	}
}
