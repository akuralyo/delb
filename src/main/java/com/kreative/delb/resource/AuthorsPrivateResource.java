package com.kreative.delb.resource;

import com.kreative.delb.functionalService.AuthorFunctionnalService;
import com.kreative.delb.resource.dto.AuthorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.kreative.delb.resource.constants.Api.*;

@RestController
@RequestMapping(API + PRIVATE + AUTHORS)
public class AuthorsPrivateResource {

	@Autowired
	private AuthorFunctionnalService authorFunctionnalService;

	@PostMapping
	public ResponseEntity create(AuthorDto authorDto) {
		return new ResponseEntity(HttpStatus.CREATED);
	}
}
