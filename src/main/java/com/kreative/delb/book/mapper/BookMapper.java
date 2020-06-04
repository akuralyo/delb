package com.kreative.delb.book.mapper;

import com.kreative.delb.author.dto.AuthorDto;
import com.kreative.delb.author.mapper.AuthorMapper;
import com.kreative.delb.book.dto.BookDto;
import com.kreative.delb.book.model.Book;
import com.kreative.delb.common.utils.Transformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BookMapper implements Transformer<Book, BookDto> {

	@Autowired
	private AuthorMapper authorMapper;

	@Override
	public BookDto mapToDto(Book book) {
		return new BookDto()
				.setIdBook(book.getIdBook())
				.setTitle(book.getTitle())
				.setAuthorDto(new AuthorDto().setIdAuthor(book.getAuthorId()));
	}

	@Override
	public Book mapToModel(BookDto source) {
		return null;
	}
}
