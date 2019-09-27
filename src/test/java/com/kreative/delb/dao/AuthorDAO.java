package com.kreative.delb.dao;

import com.kreative.delb.model.Author;
import com.kreative.delb.objectMother.AuthorMother;
import com.kreative.delb.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AuthorDAO {

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private BookDAO bookDAO;

	public void deleteAll() {
		authorRepository.deleteAll();
	}

	public List<Author> findAll() {
		List<Author> authors = new ArrayList<>();
		authorRepository.findAll().forEach(author -> {
			authors.add(author);
		});
		return authors;
	}

	public List<Author> initDb(int max) {
		List<Author> authorList = new ArrayList<>();
		for (int i = 0; i < max; i++) {
			Author author = authorRepository.save(new AuthorMother().createAuthor(i));
			bookDAO.initDb(author.getId());
			authorList.add(author);
		}
		return authorList;
	}
}
