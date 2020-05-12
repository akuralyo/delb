package com.kreative.delb.author.resource;

import com.fasterxml.jackson.annotation.JsonView;
import com.kreative.delb.author.model.QAuthor;
import com.kreative.delb.author.service.AuthorFunctionnalService;
import com.kreative.delb.common.resource.ViewsAuthor;
import com.kreative.delb.common.resource.dto.AuthorDto;
import com.querydsl.core.types.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.kreative.delb.common.resource.constants.Api.PREFIXE;
import static com.kreative.delb.common.resource.constants.Api.PUBLIC;
import static com.kreative.delb.common.resource.constants.Api.PathVariable.PV_ID;
import static com.kreative.delb.common.resource.constants.Api.Resource.AUTHORS;
import static com.kreative.delb.common.resource.constants.Api.Resource.SEARCH;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(PREFIXE + PUBLIC + AUTHORS)
public class AuthorsPublicResource {

	@Autowired
	private AuthorFunctionnalService authorFunctionnalService;

	@PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity create() {
		return new ResponseEntity(HttpStatus.NOT_IMPLEMENTED);
	}

	@DeleteMapping(value = PV_ID, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity delete() {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

	@GetMapping(produces = APPLICATION_JSON_VALUE)
	@JsonView(ViewsAuthor.ApiPublic.class)
	public List<AuthorDto> findAll() {
		return authorFunctionnalService.findAll();
	}

	@GetMapping(value = SEARCH, produces = APPLICATION_JSON_VALUE)
	@JsonView(ViewsAuthor.ApiPublic.class)
	public List<AuthorDto> findAllByPredicate(@QuerydslPredicate(root = QAuthor.class) Predicate predicate) {
		return new ArrayList<>();
	}

	@GetMapping(value = PV_ID, produces = APPLICATION_JSON_VALUE)
	@JsonView(ViewsAuthor.ApiPublic.class)
	public ResponseEntity findOne(@PathVariable String id) {
		AuthorDto authorDto = authorFunctionnalService.findOneById(id);
		return new ResponseEntity<>(authorDto, HttpStatus.OK);
	}

	@PutMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity update(@RequestBody AuthorDto authorDto) {
		return new ResponseEntity(HttpStatus.NOT_IMPLEMENTED);
	}
}