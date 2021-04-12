package com.kreative.delb.application.author.resource;

import com.fasterxml.jackson.annotation.JsonView;
import com.kreative.delb.application.author.adapter.AuthorDtoAdapter;
import com.kreative.delb.application.author.dto.AuthorDto;
import com.kreative.delb.application.common.AbstractApiRessource;
import com.kreative.delb.application.common.ApiGroupValidation;
import com.kreative.delb.application.common.ApiRestPathVariable;
import com.kreative.delb.application.common.ApiRestRessource;
import com.kreative.delb.application.common.resource.ViewsAuthor;
import com.kreative.delb.application.common.resource.constants.Api;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(Api.PREFIXE + Api.PRIVATE + Api.Resource.AUTHORS)
public class AuthorsPrivateResource extends AbstractApiRessource<AuthorDto>
    implements ApiRestRessource<AuthorDto> {

  private final AuthorDtoAdapter authorDtoAdapter;

  public AuthorsPrivateResource(final AuthorDtoAdapter authorDtoAdapter) {
    this.authorDtoAdapter = authorDtoAdapter;
  }

  @Override
  public ResponseEntity<AuthorDto> create(final AuthorDto objectDto) {
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  @Override
  public ResponseEntity delete(@PathVariable final String id) {
    authorDtoAdapter.deleteAuthor(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }

  @Override
  @JsonView(ViewsAuthor.ApiPrivate.class)
  public ResponseEntity<List<AuthorDto>> findAll() {
    final List<AuthorDto> authorDtoList = authorDtoAdapter.findAll();
    return new ResponseEntity<>(authorDtoList, HttpStatus.OK);
  }

  @Override
  public ResponseEntity<List<HashMap<String, Object>>> findAllAndFilterAplly(
      final List<String> filterList) {
    return null;
  }

  @Override
  public ResponseEntity<AuthorDto> findOneById(final String id) {
    final AuthorDto authorDto = authorDtoAdapter.findOneById(id);
    return new ResponseEntity<>(authorDto, HttpStatus.OK);
  }

  @Override
  @JsonView(ViewsAuthor.ApiPrivate.class)
  public ResponseEntity findOneByIdAndFilterApply(final String id, final List<String> filterList) {
    final AuthorDto authorDto = authorDtoAdapter.findOneById(id);
    return new ResponseEntity<>(filter(authorDto, filterList), HttpStatus.OK);
  }

  @Override
  @JsonView(ViewsAuthor.ApiPrivate.class)
  public ResponseEntity update(final String id, final AuthorDto objectDto) {
    final AuthorDto authorDtoUpdated = authorDtoAdapter.updateAuthorById(id, objectDto);
    return new ResponseEntity<>(authorDtoUpdated, HttpStatus.OK);
  }

  @JsonView(ViewsAuthor.ApiPrivate.class)
  @PutMapping(
      value = ApiRestPathVariable.PV_SELF,
      consumes = APPLICATION_JSON_VALUE,
      produces = APPLICATION_JSON_VALUE)
  public ResponseEntity updateCurrentUser(
      final Principal principal,
      @RequestBody @Validated(ApiGroupValidation.IPut.class) final AuthorDto authorDto) {
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }
}
