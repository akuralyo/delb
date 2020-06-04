package com.kreative.delb.author.dao;

import com.kreative.delb.author.model.Author;
import com.kreative.delb.author.repository.AuthorRepository;
import com.kreative.delb.book.dao.BookDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

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
		authorRepository.findAll().forEach(authors::add);
		return authors;
	}

	public Author findAnyone() {
		List<Author> authorList = findAll();
		return authorList.get(new Random().nextInt(authorList.size()));
	}

	public Author findOne(String id) {
		Optional<Author> authorOptional = authorRepository.findById(id);
		return authorOptional.orElse(null);
	}
}
