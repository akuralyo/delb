package com.kreative.delb.service.technical;

import com.kreative.delb.mapper.AuthorMapper;
import com.kreative.delb.model.Author;
import com.kreative.delb.repository.AuthorRepository;
import com.kreative.delb.resource.dto.AuthorDto;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
		return authorRepository.save(createOrUpdateAuthor(null, authorDto));
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

	public Author findOneById(String id) {
		Optional<Author> authorOptional = authorRepository.findById(id);
		return authorOptional.orElse(null);
	}

	public Author updateById(String id, AuthorDto authorDto) {
		Optional<Author> authorOptional = authorRepository.findById(id);
		if (authorOptional.isPresent()) {
			return authorRepository.save(createOrUpdateAuthor(authorOptional.get().getId(), authorDto));
		} else {
			LOGGER.warn("Auteur not found");
			return null;
		}
	}

	public Author updateByUsername(String username, AuthorDto authorDto) {
		Optional<Author> authorOptional = authorRepository.findOneByUserId(username);
		if (authorOptional.isPresent()) {
			return authorRepository.save(createOrUpdateAuthor(authorOptional.get().getId(), authorDto));
		} else {
			LOGGER.warn("Auteur not found");
			return null;
		}
	}

	private Author createOrUpdateAuthor(ObjectId id, AuthorDto authorDto) {
		return new Author().setId(id)
				.setFirstName(authorDto.getFirstName())
				.setLastName(authorDto.getLastName())
				.setNickName(authorDto.getNickName())
				.setBirthday(authorDto.getBirthday())
				.setAdresse(authorDto.getAdresse());
	}
}
