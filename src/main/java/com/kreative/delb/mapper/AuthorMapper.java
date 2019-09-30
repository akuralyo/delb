package com.kreative.delb.mapper;

import com.kreative.delb.model.Author;
import com.kreative.delb.resource.dto.AuthorDto;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper {

	public AuthorDto mapToDto(Author author) {
		if (author != null) {
			return new AuthorDto()
					.setId(author.getId().toString())
					.setFirstName(author.getFirstName())
					.setLastName(author.getLastName())
					.setNickName(author.getNickName())
					.setBirthday(author.getBirthday())
					.setAdresse(author.getAdresse());
		} else {
			return null;
		}
	}

	@Deprecated
	public Author mapToModel(AuthorDto authorDto) {
		return new Author()
				.setFirstName(authorDto.getFirstName())
				.setLastName(authorDto.getLastName())
				.setNickName(authorDto.getNickName())
				.setBirthday(authorDto.getBirthday())
				.setAdresse(authorDto.getAdresse());
	}
}
