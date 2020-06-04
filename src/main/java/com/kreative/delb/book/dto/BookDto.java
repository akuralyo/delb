package com.kreative.delb.book.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.kreative.delb.author.dto.AuthorDto;
import com.kreative.delb.common.resource.ViewsAuthor;

public class BookDto {

	@JsonView(ViewsAuthor.ApiPublic.class)
	private String idBook;

	@JsonView(ViewsAuthor.ApiPublic.class)
	private String title;

	@JsonView(ViewsAuthor.ApiPublic.class)
	private AuthorDto authorDto = new AuthorDto();

	public String getTitle() {
		return title;
	}

	public BookDto setTitle(String title) {
		this.title = title;
		return this;
	}

	public String getIdBook() {
		return idBook;
	}

	public BookDto setIdBook(String idBook) {
		this.idBook = idBook;
		return this;
	}

	public AuthorDto getAuthorDto() {
		return authorDto;
	}

	public BookDto setAuthorDto(AuthorDto authorDto) {
		this.authorDto = authorDto;
		return this;
	}
}
