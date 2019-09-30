package com.kreative.delb.resource.books;

import com.kreative.delb.resource.dto.AuthorDto;
import com.kreative.delb.resource.dto.BookDto;
import com.kreative.delb.service.functionnal.BooksFunctionnalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import static com.kreative.delb.resource.constants.Api.PREFIXE;
import static com.kreative.delb.resource.constants.Api.PRIVATE;
import static com.kreative.delb.resource.constants.Api.PathVariable.PV_ID;
import static com.kreative.delb.resource.constants.Api.Resource.AUTHORS;
import static com.kreative.delb.resource.constants.Api.Resource.BOOKS;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(PREFIXE + PRIVATE + BOOKS)
public class BooksPrivateResource {

	@Autowired
	private BooksFunctionnalService booksFunctionnalService;

	@PostMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity create(@RequestBody BookDto bookDto) {
		BookDto bookDtoCreated = booksFunctionnalService.createBook(bookDto);
		return new ResponseEntity<>(bookDtoCreated, HttpStatus.CREATED);
	}

	@DeleteMapping(value = PV_ID, consumes = APPLICATION_JSON_VALUE)
	public ResponseEntity delete() {
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping(value = PV_ID, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity update(@RequestBody BookDto bookDto) {
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping(value = PV_ID + AUTHORS, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity updateAuthor(@RequestBody AuthorDto authorDto) {
		throw new HttpClientErrorException(HttpStatus.NOT_IMPLEMENTED);
	}
}

