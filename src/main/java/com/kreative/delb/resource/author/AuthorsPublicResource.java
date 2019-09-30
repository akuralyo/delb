package com.kreative.delb.resource.author;

import com.fasterxml.jackson.annotation.JsonView;
import com.kreative.delb.resource.ViewsAuthor;
import com.kreative.delb.resource.dto.AuthorDto;
import com.kreative.delb.service.functionnal.AuthorFunctionnalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.kreative.delb.resource.constants.Api.PREFIXE;
import static com.kreative.delb.resource.constants.Api.PUBLIC;
import static com.kreative.delb.resource.constants.Api.PathVariable.PV_ID;
import static com.kreative.delb.resource.constants.Api.Resource.AUTHORS;
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
