package com.kreative.delb.service.functionnal;

import com.kreative.delb.model.Author;
import com.kreative.delb.repository.AuthorRepository;
import com.kreative.delb.resource.dto.BookDto;
import com.kreative.delb.service.technical.AuthorTechnicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BooksFunctionnalService {

	@Autowired
	private AuthorTechnicalService authorTechnicalService;

	@Autowired
	private AuthorRepository authorRepository;

	public BookDto createBook(BookDto bookDto) {
		// Vérification que l'auteur existe déjà
		Optional<Author> authorOptional = authorRepository.findOneByFirstName(bookDto.getAuthorDto().getFirstName());
		if (!authorOptional.isPresent()) {
			authorTechnicalService.createAuthor(bookDto.getAuthorDto());
		}
		// Final step : Création du livre en base
		return new BookDto();
	}
}
