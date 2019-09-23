package com.kreative.delb.mapper;

import com.kreative.delb.model.Author;
import com.kreative.delb.resource.dto.AuthorDto;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

	public AuthorDto mapToDto(Author author) {
		return new AuthorDto()
				.setFirstName(author.getFirstName())
				.setLastName(author.getLastName());
	}
}
