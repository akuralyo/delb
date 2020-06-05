package com.kreative.delb.author.objectMother;

import com.kreative.delb.author.dto.AuthorDto;
import com.kreative.delb.author.model.Author;

import java.time.LocalDate;
import java.util.*;

public class AuthorMother {

	public Author createAuthor(int i, String userId) {
		return new Author()
				.setIdAuthor(userId)
				.setFirstName("FirstName" + i)
				.setLastName("LastName" + i)
				.setNickName("NickName" + i)
				.setBirthday(LocalDate.now())
				.setAdresse("Adresse" + i)
				.setAdresse("Adresse" + i);
	}

	public Author createAuthor(int i) {
		return new Author()
				.setIdAuthor(UUID.randomUUID().toString())
				.setFirstName("FirstName" + i)
				.setLastName("LastName" + i)
				.setNickName("NickName" + i)
				.setBirthday(LocalDate.now())
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
				.setIdAuthor(author.getIdAuthor())
				.setFirstName(author.getFirstName())
				.setLastName(author.getLastName())
				.setNickName(author.getNickName())
				.setBirthday(author.getBirthday())
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
