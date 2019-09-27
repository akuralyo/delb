package com.kreative.delb.objectMother;

import com.kreative.delb.model.Book;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class BookMother {

	public Book createBook(int i, ObjectId authorId) {
		return new Book()
				.setId(new ObjectId())
				.setName("Mon livre N°" + i + " de " + authorId.toString())
				.setAuthorId(authorId);
	}

	public List<Book> createBookList(ObjectId authorId) {
		List<Book> bookList = new ArrayList<>();
		bookList.add(createBook(0, authorId));
		bookList.add(createBook(1, authorId));
		bookList.add(createBook(2, authorId));
		return bookList;
	}

	public List<Book> createBookList() {
		List<Book> bookList = new ArrayList<>();
		bookList.add(createBook(0, new ObjectId()));
		bookList.add(createBook(1, new ObjectId()));
		bookList.add(createBook(2, new ObjectId()));
		return bookList;
	}
}
