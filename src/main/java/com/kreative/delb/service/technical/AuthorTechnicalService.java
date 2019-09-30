package com.kreative.delb.service.technical;

import com.kreative.delb.mapper.AuthorMapper;
import com.kreative.delb.model.Author;
import com.kreative.delb.repository.AuthorRepository;
import com.kreative.delb.resource.dto.AuthorDto;
import org.apache.log4j.Logger;
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
		return authorRepository.save(authorMapper.mapToModel(authorDto));
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

	public Author updateAuthor(String id, AuthorDto authorDto) {
		Optional<Author> authorOptional = authorRepository.findById(id);
		if (authorOptional.isPresent()) {
			return authorRepository.save(authorMapper.mapToModel(authorDto.setId(id)));
		} else {
			LOGGER.warn("Auteur not found");
			return null;
		}
	}
}
