package com.kreative.delb.book.mapper;

import com.kreative.delb.author.mapper.AuthorMapper;
import com.kreative.delb.book.model.Book;
import com.kreative.delb.common.resource.dto.AuthorDto;
import com.kreative.delb.common.resource.dto.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookMapper {

	@Autowired
	private AuthorMapper authorMapper;

	public BookDto mapToDto(Book book) {
		return new BookDto()
				.setId(book.getId().toString())
				.setName(book.getName())
				.setAuthorDto(new AuthorDto().setId(book.getAuthorId().toString()));
	}
}
