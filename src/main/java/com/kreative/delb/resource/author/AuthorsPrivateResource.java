package com.kreative.delb.resource.author;

import com.fasterxml.jackson.annotation.JsonView;
import com.kreative.delb.resource.GroupValidation;
import com.kreative.delb.resource.ViewsAuthor;
import com.kreative.delb.resource.dto.AuthorDto;
import com.kreative.delb.service.functionnal.AuthorFunctionnalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.kreative.delb.resource.constants.Api.PREFIXE;
import static com.kreative.delb.resource.constants.Api.PRIVATE;
import static com.kreative.delb.resource.constants.Api.PathVariable.PV_ID;
import static com.kreative.delb.resource.constants.Api.Resource.AUTHORS;

@RestController
@RequestMapping(PREFIXE + PRIVATE + AUTHORS)
public class AuthorsPrivateResource {

	@Autowired
	private AuthorFunctionnalService authorFunctionnalService;

	@JsonView(ViewsAuthor.ApiPrivate.class)
	@PostMapping
	public ResponseEntity create(@RequestBody @Validated(GroupValidation.IPost.class) AuthorDto authorDto) {
		AuthorDto authorDtoCreated = authorFunctionnalService.createAuthor(authorDto);
		return new ResponseEntity<>(authorDtoCreated, HttpStatus.CREATED);
	}

	@DeleteMapping(PV_ID)
	public ResponseEntity delete() {
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@JsonView(ViewsAuthor.ApiPrivate.class)
	@GetMapping
	public List<AuthorDto> findAll() {
		return authorFunctionnalService.findAll();
	}

	@JsonView(ViewsAuthor.ApiPrivate.class)
	@GetMapping(PV_ID)
	public ResponseEntity findOne(@PathVariable String id) {
		AuthorDto authorDto = authorFunctionnalService.findOneById(id);
		return new ResponseEntity<>(authorDto, HttpStatus.OK);
	}

	@JsonView(ViewsAuthor.ApiPrivate.class)
	@PutMapping(PV_ID)
	public ResponseEntity update(@PathVariable String id, @RequestBody @Validated(GroupValidation.IPut.class) AuthorDto authorDto) {
		AuthorDto authorDtoUpdated = authorFunctionnalService.updateAuthor(id, authorDto);
		return new ResponseEntity<>(authorDtoUpdated, HttpStatus.OK);
	}
}
