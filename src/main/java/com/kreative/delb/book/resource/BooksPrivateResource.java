package com.kreative.delb.book.resource;

import com.kreative.delb.author.dto.AuthorDto;
import com.kreative.delb.book.dto.BookDto;
import com.kreative.delb.book.service.BooksFunctionnalService;
import com.kreative.delb.common.utils.RestApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.kreative.delb.common.resource.constants.Api.PREFIXE;
import static com.kreative.delb.common.resource.constants.Api.PRIVATE;
import static com.kreative.delb.common.resource.constants.Api.PathVariable.PV_ID;
import static com.kreative.delb.common.resource.constants.Api.Resource.AUTHORS;
import static com.kreative.delb.common.resource.constants.Api.Resource.BOOKS;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(PREFIXE + PRIVATE + BOOKS)
public class BooksPrivateResource implements RestApi<BookDto> {

	@Autowired
	private BooksFunctionnalService booksFunctionnalService;

	@Override
	public ResponseEntity create(@RequestBody BookDto bookDto) {
		BookDto bookDtoCreated = booksFunctionnalService.createBook(bookDto);
		return new ResponseEntity<>(bookDtoCreated, HttpStatus.CREATED);
	}

	@Override
	public ResponseEntity delete(String id) {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

	@Override
	public ResponseEntity<List<BookDto>> findAll() {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

	@Override
	public ResponseEntity<BookDto> findOneById(String id) {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

	@Override
	public ResponseEntity update(String id, BookDto authorDto) {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

	@PutMapping(value = PV_ID + AUTHORS, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity updateAuthor(@RequestBody AuthorDto authorDto) {
		return new ResponseEntity(HttpStatus.NOT_IMPLEMENTED);
	}
}

