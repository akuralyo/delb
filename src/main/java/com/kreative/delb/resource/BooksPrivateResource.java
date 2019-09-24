package com.kreative.delb.resource;

import com.kreative.delb.resource.dto.AuthorDto;
import com.kreative.delb.resource.dto.BookDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.kreative.delb.resource.constants.Api.*;

@RestController
@RequestMapping(API + PRIVATE + BOOKS)
public class BooksPrivateResource {
	@PostMapping
	public ResponseEntity create(BookDto bookDto) {
		return new ResponseEntity<>(HttpStatus.CREATED);
	}

	@RequestMapping(PV_ID)
	public class BookResource {
		@PutMapping
		public ResponseEntity update(BookDto bookDto) {
			return new ResponseEntity<>(HttpStatus.OK);
		}

		@RequestMapping(AUTHORS)
		public class AuthorRessource {
			@PutMapping
			public ResponseEntity updateAuthor(AuthorDto authorDto) {
				return new ResponseEntity<>(HttpStatus.OK);
			}
		}
	}
}
