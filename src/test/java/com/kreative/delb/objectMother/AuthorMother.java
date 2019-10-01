package com.kreative.delb.objectMother;

import com.kreative.delb.model.Author;
import com.kreative.delb.resource.dto.AuthorDto;
import org.bson.types.ObjectId;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AuthorMother {

	public Author createAuthor(int i) {
		return new Author()
				.setId(new ObjectId())
				.setFirstName("FirstName" + i)
				.setLastName("LastName" + i)
				.setNickName("NickName" + i)
				.setAdresse("Adresse" + i);
	}

	public AuthorDto createAuthorDto(int i) {
		return new AuthorDto()
				.setFirstName("FirstName" + i)
				.setLastName("LastName" + i)
				.setNickName("NickName" + i)
				.setAdresse("Mon adresse" + i);
	}

	public AuthorDto createAuthorDto(Author author) {
		return new AuthorDto()
				.setFirstName(author.getFirstName())
				.setLastName(author.getLastName())
				.setNickName(author.getNickName())
				.setAdresse(author.getAdresse());
	}

	public List<Author> createAuthorList() {
		List<Author> authorList = new ArrayList<>();
		authorList.add(createAuthor(0));
		authorList.add(createAuthor(1));
		authorList.add(createAuthor(2));
		return authorList;
	}

	public Map<String, String> createMapAuthorDto(int i) {
		Map<String, String> map = new HashMap<>();
		//
		map.put("firstName", "FirstName" + i);
		map.put("lastName", "LastName" + i);
		map.put("nickName", "NickName" + i);
		//
		return map;
	}
}
