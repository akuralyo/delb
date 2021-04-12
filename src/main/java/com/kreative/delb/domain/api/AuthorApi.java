package com.kreative.delb.domain.api;

import com.kreative.delb.application.author.dto.AuthorDto;
import com.kreative.delb.domain.service.author.model.Author;

import java.util.List;

public interface AuthorApi {

	void deleteAuthor(final String id);

	List<AuthorDto> findAll();

	AuthorDto findOneById(final String id);

	AuthorDto updateAuthorById(final String id, final Author author);
}
