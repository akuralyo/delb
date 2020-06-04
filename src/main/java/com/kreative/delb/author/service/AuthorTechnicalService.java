package com.kreative.delb.author.service;

import com.kreative.delb.author.dto.AuthorDto;
import com.kreative.delb.author.mapper.AuthorMapper;
import com.kreative.delb.author.model.Author;
import com.kreative.delb.author.repository.AuthorRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class AuthorTechnicalService {

	private static final Logger LOGGER = Logger.getLogger(AuthorTechnicalService.class);

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private AuthorMapper authorMapper;

	public Author createAuthor(Author author) {
		return authorRepository.save(author);
	}

	public Author createAuthor(AuthorDto authorDto) {
		return authorRepository.save(createOrUpdateAuthor(UUID.randomUUID().toString(), authorDto));
	}

	public void deleteAuthor(String id) {
		authorRepository.deleteById(id);
	}

	public List<Author> findAll() {
		LOGGER.debug("Accès à la méthode");
		List<Author> authors = new ArrayList<>();
		authorRepository.findAll().forEach(authors::add);
		LOGGER.debug("Nb d'éléments : " + authors.size());
		return authors;
	}

	public Author findOneById(String idAuthor) {
		Optional<Author> authorOptional = authorRepository.findOneByIdAuthor(idAuthor);
		return authorOptional.orElse(null);
	}

	public Author updateById(String id, AuthorDto authorDto) {
		Optional<Author> authorOptional = authorRepository.findById(id);
		if (authorOptional.isPresent()) {
			return authorRepository.save(createOrUpdateAuthor(authorOptional.get().getIdAuthor(), authorDto));
		} else {
			LOGGER.warn("Auteur not found");
			return null;
		}
	}

	public Author updateByUsername(String username, AuthorDto authorDto) {
		Optional<Author> authorOptional = authorRepository.findOneByIdAuthor(username);
		if (authorOptional.isPresent()) {
			return authorRepository.save(createOrUpdateAuthor(authorOptional.get().getIdAuthor(), authorDto));
		} else {
			LOGGER.warn("Auteur not found");
			return null;
		}
	}

	private Author createOrUpdateAuthor(String IdAuthor, AuthorDto authorDto) {
		return new Author()
				.setIdAuthor(IdAuthor)
				.setFirstName(authorDto.getFirstName())
				.setLastName(authorDto.getLastName())
				.setNickName(authorDto.getNickName())
				.setBirthday(authorDto.getBirthday())
				.setAdresse(authorDto.getAdresse());
	}
}
