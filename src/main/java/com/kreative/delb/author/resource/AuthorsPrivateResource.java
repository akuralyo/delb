package com.kreative.delb.author.resource;

import com.fasterxml.jackson.annotation.JsonView;
import com.kreative.delb.author.service.AuthorFunctionnalService;
import com.kreative.delb.common.resource.GroupValidation;
import com.kreative.delb.common.resource.ViewsAuthor;
import com.kreative.delb.common.resource.dto.AuthorDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import static com.kreative.delb.common.resource.constants.Api.PREFIXE;
import static com.kreative.delb.common.resource.constants.Api.PRIVATE;
import static com.kreative.delb.common.resource.constants.Api.PathVariable.PV_ID;
import static com.kreative.delb.common.resource.constants.Api.PathVariable.PV_SELF;
import static com.kreative.delb.common.resource.constants.Api.Resource.AUTHORS;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(PREFIXE + PRIVATE + AUTHORS)
public class AuthorsPrivateResource {

	private final AuthorFunctionnalService authorFunctionnalService;

	public AuthorsPrivateResource(AuthorFunctionnalService authorFunctionnalService) {
		this.authorFunctionnalService = authorFunctionnalService;
	}

	@PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity create() {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

	@DeleteMapping(value = PV_ID, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity delete(@PathVariable String id) {
		authorFunctionnalService.deleteAuthor(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@JsonView(ViewsAuthor.ApiPrivate.class)
	@GetMapping(produces = APPLICATION_JSON_VALUE)
	public List<AuthorDto> findAll() {
		return authorFunctionnalService.findAll();
	}

	@JsonView(ViewsAuthor.ApiPrivate.class)
	@GetMapping(value = PV_ID, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity findOne(@PathVariable String id) {
		AuthorDto authorDto = authorFunctionnalService.findOneById(id);
		return new ResponseEntity<>(authorDto, HttpStatus.OK);
	}

	@JsonView(ViewsAuthor.ApiPrivate.class)
	@PutMapping(value = PV_ID, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity update(@PathVariable String id, @RequestBody @Validated(GroupValidation.IPut.class) AuthorDto authorDto) {
		AuthorDto authorDtoUpdated = authorFunctionnalService.updateAuthorById(id, authorDto);
		return new ResponseEntity<>(authorDtoUpdated, HttpStatus.OK);
	}

	@JsonView(ViewsAuthor.ApiPrivate.class)
	@PutMapping(value = PV_SELF, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity updateCurrentUser(Principal principal, @RequestBody @Validated(GroupValidation.IPut.class) AuthorDto authorDto) {
		AuthorDto authorDtoUpdated = authorFunctionnalService.updateAuthorByUsername(principal.getName(), authorDto);
		return new ResponseEntity<>(authorDtoUpdated, HttpStatus.OK);
	}
}
