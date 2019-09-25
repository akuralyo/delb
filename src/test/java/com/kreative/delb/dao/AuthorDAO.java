package com.kreative.delb.dao;

import com.kreative.delb.objectMother.AuthorMother;
import com.kreative.delb.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AuthorDAO {

	@Autowired
	private AuthorRepository authorRepository;

	public void deleteAll() {
		authorRepository.deleteAll();
	}

	public void initDb(int max) {
		for (int i = 0; i < max; i++) {
			authorRepository.save(new AuthorMother().createAuthor(i));
		}
	}
}
