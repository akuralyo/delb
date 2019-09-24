package com.kreative.delb.resource;

import com.kreative.delb.functionalService.AuthorFunctionnalService;
import com.kreative.delb.resource.dto.AuthorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static com.kreative.delb.resource.constants.Api.*;

@RestController
@RequestMapping(API + PUBLIC + AUTHORS)
public class AuthorsPublicResource {

	@Autowired
	private AuthorFunctionnalService authorFunctionnalService;

	@GetMapping
	public List<AuthorDto> findAll() {
		return new ArrayList<>();
	}

	@GetMapping(PV_ID)
	public ResponseEntity<AuthorDto> get(@PathVariable String id) {
		AuthorDto authorDto = authorFunctionnalService.findOneById(id);
		if (authorDto == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<>(authorDto, HttpStatus.OK);
		}
	}
}