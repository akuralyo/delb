package com.kreative.delb.resource.books;

import com.kreative.delb.resource.dto.AuthorDto;
import com.kreative.delb.resource.dto.BookDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;

import static com.kreative.delb.resource.constants.Api.*;

@RestController
@RequestMapping(API + PRIVATE + BOOKS)
public class BooksPrivateResource {
	@PostMapping
	public ResponseEntity create(@RequestBody BookDto bookDto) {
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@PutMapping(PV_ID)
	public ResponseEntity update(@RequestBody BookDto bookDto) {
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PutMapping(PV_ID + AUTHORS)
	public ResponseEntity updateAuthor(@RequestBody AuthorDto authorDto) {
		throw new HttpClientErrorException(HttpStatus.NOT_IMPLEMENTED);
	}

	@DeleteMapping(PV_ID)
	public ResponseEntity delete() {
		return new ResponseEntity<>(HttpStatus.OK);
	}
}

