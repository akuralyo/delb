package com.kreative.delb.author.mapper;

import com.kreative.delb.author.dto.AuthorDto;
import com.kreative.delb.author.model.Author;
import com.kreative.delb.common.utils.Transformer;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper implements Transformer<Author, AuthorDto> {

	@Override
	public AuthorDto mapToDto(Author source) {
		if (source != null) {
			return new AuthorDto()
					.setIdAuthor(source.getIdAuthor())
					.setFirstName(source.getFirstName())
					.setLastName(source.getLastName())
					.setNickName(source.getNickName())
					.setBirthday(source.getBirthday())
					.setAdresse(source.getAdresse());
		} else {
			return null;
		}
	}

	@Override
	public Author mapToModel(AuthorDto source) {
		if (source != null) {
			return new Author()
					.setFirstName(source.getFirstName())
					.setLastName(source.getLastName())
					.setNickName(source.getNickName())
					.setBirthday(source.getBirthday())
					.setAdresse(source.getAdresse());
		} else {
			return null;
		}
	}
}
