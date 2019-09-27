package com.kreative.delb.resource.books;

import com.kreative.delb.resource.dto.AuthorDto;
import com.kreative.delb.resource.dto.BookDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import static com.kreative.delb.resource.constants.Api.PREFIXE;
import static com.kreative.delb.resource.constants.Api.PRIVATE;
import static com.kreative.delb.resource.constants.Api.PathVariable.PV_ID;
import static com.kreative.delb.resource.constants.Api.Resource.AUTHORS;
import static com.kreative.delb.resource.constants.Api.Resource.BOOKS;

@RestController
@RequestMapping(PREFIXE + PRIVATE + BOOKS)
public class BooksPrivateResource {
	@PostMapping
	public ResponseEntity create(@RequestBody BookDto bookDto) {
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@DeleteMapping(PV_ID)
	public ResponseEntity delete() {
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping(PV_ID)
	public ResponseEntity update(@RequestBody BookDto bookDto) {
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping(PV_ID + AUTHORS)
	public ResponseEntity updateAuthor(@RequestBody AuthorDto authorDto) {
		throw new HttpClientErrorException(HttpStatus.NOT_IMPLEMENTED);
	}
}

