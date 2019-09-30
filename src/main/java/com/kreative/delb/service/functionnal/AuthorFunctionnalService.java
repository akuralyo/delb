package com.kreative.delb.service.functionnal;

import com.kreative.delb.mapper.AuthorMapper;
import com.kreative.delb.mapper.BookMapper;
import com.kreative.delb.model.Author;
import com.kreative.delb.model.Book;
import com.kreative.delb.resource.dto.AuthorDto;
import com.kreative.delb.service.technical.AuthorTechnicalService;
import com.kreative.delb.service.technical.BookTechnicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorFunctionnalService {

	@Autowired
	private AuthorTechnicalService authorTechnicalService;

	@Autowired
	private BookTechnicalService bookTechnicalService;

	@Autowired
	private AuthorMapper authorMapper;

	@Autowired
	private BookMapper bookMapper;

	public AuthorDto createAuthor(AuthorDto authorDto) {
		return authorMapper.mapToDto(authorTechnicalService.createAuthor(authorDto));
	}

	public void deleteAuthor(String id) {
		Author author = authorTechnicalService.findOneById(id);
		if (author != null) {
			authorTechnicalService.deleteAuthor(id);
		} else {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
	}

	public List<AuthorDto> findAll() {
		return authorTechnicalService.findAll().stream()
				.map(author -> authorMapper.mapToDto(author)).map(authorDto -> {
					List<Book> bookList = bookTechnicalService.findAllByAuthorId(authorDto.getId());
					bookTechnicalService.findAllByAuthorId(authorDto.getId()).stream().forEach(book -> {
						authorDto.getBookDtoList().add(bookMapper.mapToDto(book));
					});
					return authorDto;
				})
				.collect(Collectors.toList());
	}

	public AuthorDto findOneById(String id) {
		Author author = authorTechnicalService.findOneById(id);
		if (author == null) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		} else {
			AuthorDto authorDto = authorMapper.mapToDto(author);
			bookTechnicalService.findAllByAuthorId(authorDto.getId()).stream().forEach(book -> {
				authorDto.getBookDtoList().add(bookMapper.mapToDto(book));
			});
			return authorDto;
		}
	}

	public AuthorDto updateAuthor(String id, AuthorDto authorDto) {
		Author authorUpdated = authorTechnicalService.updateAuthor(id, authorDto);
		if (authorUpdated != null) {
			return authorMapper.mapToDto(authorUpdated);
		} else {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
	}
}
