package com.kreative.delb.technicalService;

import com.kreative.delb.mapper.AuthorMapper;
import com.kreative.delb.model.Author;
import com.kreative.delb.repository.AuthorRepository;
import com.kreative.delb.resource.dto.AuthorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AuthorTechnicalService {

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private AuthorMapper authorMapper;

	public List<AuthorDto> findAll() {
		List<AuthorDto> authorDtos = new ArrayList<>();
		authorRepository.findAll().forEach(author -> {
			authorDtos.add(authorMapper.mapToDto(author));
		});
		return authorDtos;
	}

	public AuthorDto findOneById(String id) {
		Optional<Author> authorOptional = authorRepository.findById(id);
		if (authorOptional.isPresent()) {
			return authorMapper.mapToDto(authorOptional.get());
		} else {
			return null;
		}
	}
}
