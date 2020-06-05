package com.kreative.delb.author.resource;

import com.fasterxml.jackson.annotation.JsonView;
import com.kreative.delb.author.dto.AuthorDto;
import com.kreative.delb.author.service.AuthorFunctionnalService;
import com.kreative.delb.common.resource.AbstractRessourceApi;
import com.kreative.delb.common.resource.GroupValidation;
import com.kreative.delb.common.resource.RestApi;
import com.kreative.delb.common.resource.ViewsAuthor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

import static com.kreative.delb.common.resource.constants.Api.PREFIXE;
import static com.kreative.delb.common.resource.constants.Api.PRIVATE;
import static com.kreative.delb.common.resource.constants.Api.PathVariable.PV_SELF;
import static com.kreative.delb.common.resource.constants.Api.Resource.AUTHORS;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(PREFIXE + PRIVATE + AUTHORS)
public class AuthorsPrivateResource extends AbstractRessourceApi<AuthorDto> implements RestApi<AuthorDto> {

	@Autowired
	private AuthorFunctionnalService authorFunctionnalService;

	@Override
	public ResponseEntity<AuthorDto> create(AuthorDto objectDto) {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

	@Override
	public ResponseEntity delete(@PathVariable String id) {
		authorFunctionnalService.deleteAuthor(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@Override
	@JsonView(ViewsAuthor.ApiPrivate.class)
	public ResponseEntity<List<AuthorDto>> findAll() {
		List<AuthorDto> authorDtoList = authorFunctionnalService.findAll();
		return new ResponseEntity<List<AuthorDto>>(authorDtoList, HttpStatus.OK);
	}

	@Override
	public ResponseEntity<AuthorDto> findOneById(String id) {
		AuthorDto authorDto = authorFunctionnalService.findOneById(id);
		return new ResponseEntity<>(authorDto, HttpStatus.OK);
	}

	@Override
	@JsonView(ViewsAuthor.ApiPrivate.class)
	public ResponseEntity findOneByIdAndFilterApply(String id, List<String> filterList) {
		AuthorDto authorDto = authorFunctionnalService.findOneById(id);
		return new ResponseEntity<>(filter(authorDto, filterList), HttpStatus.OK);
	}

	@Override
	@JsonView(ViewsAuthor.ApiPrivate.class)
	public ResponseEntity update(String id, AuthorDto objectDto) {
		AuthorDto authorDtoUpdated = authorFunctionnalService.updateAuthorById(id, objectDto);
		return new ResponseEntity<>(authorDtoUpdated, HttpStatus.OK);
	}

	@JsonView(ViewsAuthor.ApiPrivate.class)
	@PutMapping(value = PV_SELF, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity updateCurrentUser(Principal principal, @RequestBody @Validated(GroupValidation.IPut.class) AuthorDto authorDto) {
		AuthorDto authorDtoUpdated = authorFunctionnalService.updateAuthorByUsername(principal.getName(), authorDto);
		return new ResponseEntity<>(authorDtoUpdated, HttpStatus.OK);
	}
}
