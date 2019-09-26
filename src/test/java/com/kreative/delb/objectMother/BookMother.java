package com.kreative.delb.objectMother;

import com.kreative.delb.model.Book;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class BookMother {

	public List<Book> createBookList() {
		List<Book> bookList = new ArrayList<>();
		bookList.add(createBook(0));
		bookList.add(createBook(1));
		bookList.add(createBook(2));
		return bookList;
	}

	public Book createBook(int i) {
		return new Book()
				.setId(new ObjectId())
				.setName("Mon livre N°" + i);
	}
}
