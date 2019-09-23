package com.kreative.delb.functionalService;

import com.kreative.delb.resource.dto.AuthorDto;
import com.kreative.delb.technicalService.AuthorTechnicalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthorFunctionnalService {

	@Autowired
	private AuthorTechnicalService authorTechnicalService;

	public List<AuthorDto> findAll() {
		return authorTechnicalService.findAll();
	}

	public ResponseEntity<AuthorDto> findOneById(String id) {
		AuthorDto authorDto = authorTechnicalService.findOneById(id);
		if (authorDto == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(authorDto, HttpStatus.OK);
		}
	}
}
