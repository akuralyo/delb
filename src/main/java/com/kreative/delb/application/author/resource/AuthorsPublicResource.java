package com.kreative.delb.application.author.resource;

import java.util.HashMap;
import java.util.List;

import com.kreative.delb.application.author.adapter.AuthorDtoAdapter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;
import com.kreative.delb.application.author.dto.AuthorDto;
import com.kreative.delb.application.common.AbstractApiRessource;
import com.kreative.delb.application.common.ApiRestRessource;
import com.kreative.delb.application.common.resource.ViewsAuthor;
import com.kreative.delb.application.common.resource.constants.Api;
import com.kreative.delb.domain.service.author.service.AuthorService;

@RestController
@RequestMapping(Api.PREFIXE + Api.PUBLIC + Api.Resource.AUTHORS)
public class AuthorsPublicResource extends AbstractApiRessource<AuthorDto>
    implements ApiRestRessource<AuthorDto> {

  private final AuthorDtoAdapter authorDtoAdapter;

  public AuthorsPublicResource(final AuthorDtoAdapter authorDtoAdapter) {
    super();
    this.authorDtoAdapter = authorDtoAdapter;
  }

  @Override
  public ResponseEntity<AuthorDto> create(final AuthorDto objectDto) {
    return new ResponseEntity(HttpStatus.FORBIDDEN);
  }

  @Override
  public ResponseEntity delete(final String id) {
    return new ResponseEntity<>(HttpStatus.FORBIDDEN);
  }

  @Override
  @JsonView(ViewsAuthor.ApiPublic.class)
  public ResponseEntity<List<AuthorDto>> findAll() {
    final List<AuthorDto> authorDtoList = authorDtoAdapter.findAll();
    return new ResponseEntity<>(authorDtoList, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<List<HashMap<String, Object>>> findAllAndFilterAplly(
      final List<String> filterList) {
    final List<AuthorDto> authorDtoList = authorDtoAdapter.findAll();
    return new ResponseEntity<>(filter(authorDtoList, filterList), HttpStatus.OK);
  }

  @Override
  @JsonView(ViewsAuthor.ApiPublic.class)
  public ResponseEntity<AuthorDto> findOneById(final String id) {
    final AuthorDto authorDto = authorDtoAdapter.findOneById(id);
    return new ResponseEntity<>(authorDto, HttpStatus.OK);
  }

  /**
   * @GetMapping(value = SEARCH, produces =
   * APPLICATION_JSON_VALUE) @JsonView(ViewsAuthor.ApiPublic.class) public List<AuthorDto>
   * findAllByPredicate(@QuerydslPredicate(root = QAuthor.class) Predicate predicate) { return new
   * ArrayList<>(); }
   */
  @Override
  @JsonView(ViewsAuthor.ApiPublic.class)
  public ResponseEntity<HashMap<String, Object>> findOneByIdAndFilterApply(
      final String id, final List<String> filterList) {
    final AuthorDto authorDto = authorDtoAdapter.findOneById(id);
    return new ResponseEntity<>(filter(authorDto, filterList), HttpStatus.OK);
  }

  @Override
  public ResponseEntity update(final String id, final AuthorDto objectDto) {
    return new ResponseEntity(HttpStatus.FORBIDDEN);
  }
}
