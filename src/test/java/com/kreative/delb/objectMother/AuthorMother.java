package com.kreative.delb.objectMother;

import com.kreative.delb.model.Author;
import com.kreative.delb.resource.dto.AuthorDto;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class AuthorMother {

	private Author createAuthor(int i) {
		return new Author()
				.setId(new ObjectId())
				.setFirstName("FirstName" + i)
				.setLastName("LastName" + i)
				.setNickName("NickName" + i)
				.setAdresse("Adresse" + i);
	}

	private AuthorDto createAuthorDto(int i) {
		return new AuthorDto()
				.setFirstName("FirstName" + i)
				.setLastName("LastName" + i)
				.setNickName("NickName" + i)
				.setAdresse("Mon adresse" + i);
	}

	private AuthorDto createAuthorDto(Author author) {
		return new AuthorDto()
				.setFirstName(author.getFirstName())
				.setLastName(author.getLastName())
				.setNickName(author.getNickName())
				.setAdresse(author.getAdresse());
	}

	private List<Author> createAuthorList() {
		List<Author> authorList = new ArrayList<>();
		authorList.add(createAuthor(0));
		authorList.add(createAuthor(1));
		authorList.add(createAuthor(2));
		return authorList;
	}
}
