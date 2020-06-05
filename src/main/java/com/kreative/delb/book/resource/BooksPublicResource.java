package com.kreative.delb.book.resource;

import com.kreative.delb.book.dto.BookDto;
import com.kreative.delb.book.service.BooksFunctionnalService;
import com.kreative.delb.common.utils.RestApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.kreative.delb.common.resource.constants.Api.PREFIXE;
import static com.kreative.delb.common.resource.constants.Api.PUBLIC;
import static com.kreative.delb.common.resource.constants.Api.Resource.BOOKS;

@RestController
@RequestMapping(PREFIXE + PUBLIC + BOOKS)
public class BooksPublicResource implements RestApi<BookDto> {

	@Autowired
	private BooksFunctionnalService booksFunctionnalService;

	@Override
	public ResponseEntity<BookDto> create(BookDto object) {
		return new ResponseEntity(HttpStatus.FORBIDDEN);
	}

	@Override
	public ResponseEntity delete(String id) {
		return new ResponseEntity(HttpStatus.FORBIDDEN);
	}

	@Override
	public ResponseEntity<List<BookDto>> findAll() {
		return new ResponseEntity(HttpStatus.NOT_IMPLEMENTED);
	}

	@Override
	public ResponseEntity<BookDto> findOneById(String id) {
		return new ResponseEntity(HttpStatus.NOT_IMPLEMENTED);
	}

	@Override
	public ResponseEntity update(String id, BookDto authorDto) {
		return new ResponseEntity(HttpStatus.NOT_IMPLEMENTED);
	}
}


