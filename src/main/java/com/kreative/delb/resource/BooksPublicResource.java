package com.kreative.delb.resource;

import com.kreative.delb.resource.dto.AuthorDto;
import com.kreative.delb.resource.dto.BookDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static com.kreative.delb.resource.constants.Api.*;

@RestController
@RequestMapping(API + PUBLIC + BOOKS)
public class BooksPublicResource {

	@GetMapping(PV_ID)
	public ResponseEntity<BookDto> get(@PathVariable String id) {
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping
	public List<BookDto> getAll() {
		return new ArrayList<>();
	}

	@GetMapping(AUTHORS)
	public ResponseEntity<AuthorDto> getAuthor() {
		return new ResponseEntity<>(HttpStatus.OK);
	}
}


