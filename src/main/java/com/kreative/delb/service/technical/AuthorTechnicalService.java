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

	private static Logger logger = Logger.getLogger(AuthorTechnicalService.class);

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private AuthorMapper authorMapper;

	public List<Author> findAll() {
		logger.debug("Accès à la méthode");
		List<Author> authors = new ArrayList<>();
		authorRepository.findAll().forEach(author -> {
			authors.add(author);
		});
		logger.debug("Nb d'éléments : " + authors.size());
		return authors;
	}

	public Author findOneById(String id) {
		Optional<Author> authorOptional = authorRepository.findById(id);
		if (authorOptional.isPresent()) {
			return authorOptional.get();
		} else {
			return null;
		}
	}

	public Author createAuthor(Author author) {
		return authorRepository.save(author);
	}

	public Author createAuthor(AuthorDto authorDto) {
		return authorRepository.save(authorMapper.mapToModel(authorDto));
	}

	public Author updateAuthor(String id, AuthorDto authorDto) {
		Optional<Author> authorOptional = authorRepository.findById(id);
		if (authorOptional.isPresent()) {
			return authorRepository.save(authorMapper.mapToModel(authorDto.setId(id)));
		} else {
			logger.warn("Auteur not found");
			return null;
		}
	}
}