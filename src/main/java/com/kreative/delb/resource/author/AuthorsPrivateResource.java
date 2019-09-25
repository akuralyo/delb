package com.kreative.delb.resource.author;

import com.fasterxml.jackson.annotation.JsonView;
import com.kreative.delb.functionalService.AuthorFunctionnalService;
import com.kreative.delb.model.Author;
import com.kreative.delb.resource.GroupValidation;
import com.kreative.delb.resource.Views;
import com.kreative.delb.resource.constants.Api;
import com.kreative.delb.resource.dto.AuthorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.kreative.delb.resource.constants.Api.*;

@RestController
@RequestMapping(API + PRIVATE + AUTHORS)
public class AuthorsPrivateResource {

	@Autowired
	private AuthorFunctionnalService authorFunctionnalService;

	@JsonView(Views.Private.class)
	@GetMapping
	public List<AuthorDto> findAll() {
		return authorFunctionnalService.findAll();
	}

	@PostMapping
	public ResponseEntity<Author> create(@RequestBody @Validated(GroupValidation.IPost.class) AuthorDto authorDto) {
		AuthorDto authorDtoCreated = authorFunctionnalService.createAuthor(authorDto);
		return new ResponseEntity(authorDtoCreated, HttpStatus.CREATED);
	}

	@PutMapping(Api.PV_ID)
	public ResponseEntity<AuthorDto> update(@PathVariable String id, @RequestBody @Validated(GroupValidation.IPut.class) AuthorDto authorDto) {
		AuthorDto authorDtoUpdated = authorFunctionnalService.updateAuthor(id, authorDto);
		return new ResponseEntity(authorDtoUpdated, HttpStatus.OK);
	}
}
