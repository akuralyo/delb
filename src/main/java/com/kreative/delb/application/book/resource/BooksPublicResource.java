package com.kreative.delb.application.book.resource;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kreative.delb.application.book.dto.BookDto;
import com.kreative.delb.application.common.AbstractApiRessource;
import com.kreative.delb.application.common.ApiRestRessource;
import com.kreative.delb.application.common.resource.constants.Api;
import com.kreative.delb.domain.service.book.service.BookService;

@RestController
@RequestMapping(Api.PREFIXE + Api.PUBLIC + Api.Resource.BOOKS)
public class BooksPublicResource extends AbstractApiRessource<BookDto>
    implements ApiRestRessource<BookDto> {

  @Autowired private BookService bookService;

  @Override
  public ResponseEntity<BookDto> create(final BookDto objectDto) {
    return new ResponseEntity(HttpStatus.FORBIDDEN);
  }

  @Override
  public ResponseEntity delete(final String id) {
    return new ResponseEntity(HttpStatus.FORBIDDEN);
  }

  @Override
  public ResponseEntity<List<BookDto>> findAll() {
    return new ResponseEntity(HttpStatus.NOT_IMPLEMENTED);
  }

  @Override
  public ResponseEntity<List<HashMap<String, Object>>> findAllAndFilterAplly(
      final List<String> filterList) {
    return null;
  }

  @Override
  public ResponseEntity<BookDto> findOneById(final String id) {
    return new ResponseEntity(HttpStatus.NOT_IMPLEMENTED);
  }

  @Override
  public ResponseEntity<HashMap<String, Object>> findOneByIdAndFilterApply(
      final String id, final List<String> filterList) {
    return new ResponseEntity(HttpStatus.NOT_IMPLEMENTED);
  }

  @Override
  public ResponseEntity update(final String id, final BookDto objectDto) {
    return new ResponseEntity(HttpStatus.NOT_IMPLEMENTED);
  }
}
