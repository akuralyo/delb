package com.kreative.delb.application.book.resource;

import com.kreative.delb.application.book.adapter.BookDtoAdapter;
import com.kreative.delb.application.book.dto.BookDto;
import com.kreative.delb.application.common.AbstractApiRessource;
import com.kreative.delb.application.common.ApiRestRessource;
import com.kreative.delb.domain.service.book.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

import static com.kreative.delb.application.common.resource.constants.Api.PREFIXE;
import static com.kreative.delb.application.common.resource.constants.Api.PRIVATE;
import static com.kreative.delb.application.common.resource.constants.Api.Resource.BOOKS;

@RestController
@RequestMapping(PREFIXE + PRIVATE + BOOKS)
public class BooksPrivateResource extends AbstractApiRessource<BookDto>
    implements ApiRestRessource<BookDto> {

  private final BookDtoAdapter bookDtoAdapter;

  public BooksPrivateResource(final BookDtoAdapter bookDtoAdapter) {
    this.bookDtoAdapter = bookDtoAdapter;
  }

  @Override
  public ResponseEntity create(@RequestBody final BookDto objectDto) {
    final BookDto bookDtoCreated = bookDtoAdapter.createBook(objectDto);
    return new ResponseEntity<>(bookDtoCreated, HttpStatus.CREATED);
  }

  @Override
  public ResponseEntity delete(final String id) {
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  @Override
  public ResponseEntity<List<BookDto>> findAll() {
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  @Override
  public ResponseEntity<List<HashMap<String, Object>>> findAllAndFilterAplly(
      final List<String> filterList) {
    return null;
  }

  @Override
  public ResponseEntity<BookDto> findOneById(final String id) {
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  @Override
  public ResponseEntity<HashMap<String, Object>> findOneByIdAndFilterApply(
      final String id, final List<String> filterList) {
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }

  @Override
  public ResponseEntity update(final String id, final BookDto objectDto) {
    return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
  }
}
