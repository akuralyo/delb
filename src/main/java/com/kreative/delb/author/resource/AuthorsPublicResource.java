package com.kreative.delb.author.resource;

import com.fasterxml.jackson.annotation.JsonView;
import com.kreative.delb.author.dto.AuthorDto;
import com.kreative.delb.author.service.AuthorFunctionnalService;
import com.kreative.delb.common.resource.AbstractRessourceApi;
import com.kreative.delb.common.resource.RestApi;
import com.kreative.delb.common.resource.ViewsAuthor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

import static com.kreative.delb.common.resource.constants.Api.PREFIXE;
import static com.kreative.delb.common.resource.constants.Api.PUBLIC;
import static com.kreative.delb.common.resource.constants.Api.Resource.AUTHORS;

@RestController
@RequestMapping(PREFIXE + PUBLIC + AUTHORS)
public class AuthorsPublicResource extends AbstractRessourceApi<AuthorDto> implements RestApi<AuthorDto> {

	@Autowired
	private AuthorFunctionnalService authorFunctionnalService;

	@Override
	public ResponseEntity<AuthorDto> create(AuthorDto objectDto) {
		return new ResponseEntity(HttpStatus.FORBIDDEN);
	}

	@Override
	public ResponseEntity delete(String id) {
		return new ResponseEntity<>(HttpStatus.FORBIDDEN);
	}

	@Override
	@JsonView(ViewsAuthor.ApiPublic.class)
	public ResponseEntity<List<AuthorDto>> findAll() {
		List<AuthorDto> authorDtoList = authorFunctionnalService.findAll();
		return new ResponseEntity<List<AuthorDto>>(authorDtoList, HttpStatus.OK);
	}

	@Override
	@JsonView(ViewsAuthor.ApiPublic.class)
	public ResponseEntity<AuthorDto> findOneById(String id) {
		AuthorDto authorDto = authorFunctionnalService.findOneById(id);
		return new ResponseEntity<>(authorDto, HttpStatus.OK);
	}

	/**
	 * @GetMapping(value = SEARCH, produces = APPLICATION_JSON_VALUE)
	 * @JsonView(ViewsAuthor.ApiPublic.class) public List<AuthorDto> findAllByPredicate(@QuerydslPredicate(root = QAuthor.class) Predicate predicate) {
	 * return new ArrayList<>();
	 * }
	 */

	@Override
	@JsonView(ViewsAuthor.ApiPublic.class)
	public ResponseEntity<HashMap<String, Object>> findOneByIdAndFilterApply(String id, List<String> filterList) {
		AuthorDto authorDto = authorFunctionnalService.findOneById(id);
		return new ResponseEntity<>(filter(authorDto, filterList), HttpStatus.OK);
	}

	@Override
	public ResponseEntity update(String id, AuthorDto objectDto) {
		return new ResponseEntity(HttpStatus.FORBIDDEN);
	}
}
