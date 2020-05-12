package com.kreative.delb.book.resource;

import com.kreative.delb.book.service.BooksFunctionnalService;
import com.kreative.delb.common.resource.dto.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

import static com.kreative.delb.common.resource.constants.Api.PREFIXE;
import static com.kreative.delb.common.resource.constants.Api.PUBLIC;
import static com.kreative.delb.common.resource.constants.Api.PathVariable.PV_ID;
import static com.kreative.delb.common.resource.constants.Api.Resource.AUTHORS;
import static com.kreative.delb.common.resource.constants.Api.Resource.BOOKS;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(PREFIXE + PUBLIC + BOOKS)
public class BooksPublicResource {

	@Autowired
	private BooksFunctionnalService booksFunctionnalService;

	@GetMapping(value = PV_ID, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity get(@PathVariable String id) {
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping(consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public List<BookDto> getAll() {
		return new ArrayList<>();
	}

	@GetMapping(value = AUTHORS, consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
	public ResponseEntity getAuthor() {
		return new ResponseEntity<>(HttpStatus.OK);
	}
}


