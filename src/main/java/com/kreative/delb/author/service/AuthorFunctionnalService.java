package com.kreative.delb.author.service;

import com.kreative.delb.author.mapper.AuthorMapper;
import com.kreative.delb.author.model.Author;
import com.kreative.delb.book.mapper.BookMapper;
import com.kreative.delb.book.model.Book;
import com.kreative.delb.book.service.BookTechnicalService;
import com.kreative.delb.common.resource.dto.AuthorDto;
import com.kreative.delb.security.UserRepository;
import com.kreative.delb.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.Optional;
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

	@Autowired
	private UserRepository userRepository;

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
				.map(authorMapper::mapToDto).peek(authorDto -> {
					List<Book> bookList = bookTechnicalService.findAllByAuthorId(authorDto.getId());
					bookTechnicalService.findAllByAuthorId(authorDto.getId())
							.forEach(book -> authorDto.getBookDtoList().add(bookMapper.mapToDto(book)));
				})
				.collect(Collectors.toList());
	}

	public AuthorDto findOneById(String id) {
		Author author = authorTechnicalService.findOneById(id);
		if (author == null) {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		} else {
			AuthorDto authorDto = authorMapper.mapToDto(author);
			bookTechnicalService.findAllByAuthorId(authorDto.getId())
					.forEach(book -> authorDto.getBookDtoList().add(bookMapper.mapToDto(book)));
			return authorDto;
		}
	}

	public AuthorDto updateAuthorById(String id, AuthorDto authorDto) {
		Author authorUpdated = authorTechnicalService.updateById(id, authorDto);
		if (authorUpdated != null) {
			return authorMapper.mapToDto(authorUpdated);
		} else {
			throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
		}
	}

	public AuthorDto updateAuthorByUsername(String username, AuthorDto authorDto) {
		Optional<User> userOptional = userRepository.findOneByUsername(username);
		if (userOptional.isPresent() && userOptional.get().getAuthorities().contains("AUTHOR")) {
			Author authorUpdated = authorTechnicalService.updateByUsername(username, authorDto);
			if (authorUpdated != null) {
				return authorMapper.mapToDto(authorUpdated);
			} else {
				throw new HttpClientErrorException(HttpStatus.NOT_FOUND);
			}
		} else {
			throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED);
		}
	}
}
