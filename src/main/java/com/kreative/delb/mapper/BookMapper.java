package com.kreative.delb.mapper;

import com.kreative.delb.model.Book;
import com.kreative.delb.resource.dto.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

	@Autowired
	private AuthorMapper authorMapper;

	public BookDto mapToDto(Book book) {
		return new BookDto()
				.setId(book.getId().toString())
				.setName(book.getName());
	}
}
