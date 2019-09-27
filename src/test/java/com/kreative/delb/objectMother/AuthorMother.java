package com.kreative.delb.objectMother;

import com.kreative.delb.model.Author;
import com.kreative.delb.resource.dto.AuthorDto;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.List;

public class AuthorMother {

	public Author createAuthor(int i) {
		return new Author()
				.setId(new ObjectId())
				.setFirstName("FirstName" + i)
				.setLastName("LastName" + i)
				.setNickName("NickName" + i);
	}

	public AuthorDto createAuthorDto(int i) {
		return new AuthorDto()
				.setFirstName("FirstName" + i)
				.setLastName("LastName" + i)
				.setNickName("NickName" + i);
	}

	public List<Author> createAuthorList() {
		List<Author> authorList = new ArrayList<>();
		authorList.add(createAuthor(0));
		authorList.add(createAuthor(1));
		authorList.add(createAuthor(2));
		return authorList;
	}
}
