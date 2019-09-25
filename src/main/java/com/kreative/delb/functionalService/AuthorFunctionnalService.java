package com.kreative.delb.functionalService;

import com.kreative.delb.mapper.AuthorMapper;
import com.kreative.delb.model.Author;
import com.kreative.delb.resource.dto.AuthorDto;
import com.kreative.delb.technicalService.AuthorTechnicalService;
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
	private AuthorMapper authorMapper;

	public List<AuthorDto> findAll() {
		return authorTechnicalService.findAll().stream()
				.map(author -> authorMapper.mapToDto(author))
				.collect(Collectors.toList());
	}

	public AuthorDto findOneById(String id) {
		Author author = authorTechnicalService.findOneById(id);
		if (author == null) {
			return null;
		} else {
			return authorMapper.mapToDto(author);
		}
	}

	public AuthorDto createAuthor(AuthorDto authorDto) {
		return authorMapper.mapToDto(authorTechnicalService.createAuthor(authorDto));
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
