package com.kreative.delb.book.service;

import com.kreative.delb.author.mapper.AuthorMapper;
import com.kreative.delb.author.model.Author;
import com.kreative.delb.author.repository.AuthorRepository;
import com.kreative.delb.author.service.AuthorTechnicalService;
import com.kreative.delb.book.mapper.BookMapper;
import com.kreative.delb.book.model.Book;
import com.kreative.delb.common.resource.dto.BookDto;
import org.apache.log4j.Logger;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BooksFunctionnalService {

	private static final Logger LOGGER = Logger.getLogger(BooksFunctionnalService.class);

	@Autowired
	private AuthorTechnicalService authorTechnicalService;

	@Autowired
	private BookTechnicalService bookTechnicalService;

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private BookMapper bookMapper;

	@Autowired
	private AuthorMapper authorMapper;

	public BookDto createBook(BookDto bookDto) {
		// Vérification que l'auteur existe déjà
		Optional<Author> authorOptional = authorRepository.findById(bookDto.getAuthorDto().getId());
		if (!authorOptional.isPresent()) {
			LOGGER.warn("Création de l'auteur");
			Author author = authorTechnicalService.createAuthor(bookDto.getAuthorDto());
			return bookMapper.mapToDto(bookTechnicalService.createBook(
					createBookFromDto(bookDto, author.getId().toString())));
		} else {
			return bookMapper.mapToDto(bookTechnicalService.createBook(
					createBookFromDto(bookDto, bookDto.getAuthorDto().getId())));
		}
	}

	public List<BookDto> findAll() {
		return bookTechnicalService.findAll().stream().map(bookMapper::mapToDto)
				.map(bookDto -> bookDto.setAuthorDto(authorMapper.mapToDto(authorTechnicalService.findOneById(bookDto.getAuthorDto().getId()))))
				.collect(Collectors.toList());
	}

	private Book createBookFromDto(BookDto bookDto, String authorId) {
		return new Book().setName(bookDto.getName()).
				setAuthorId(new ObjectId(authorId));
	}
}