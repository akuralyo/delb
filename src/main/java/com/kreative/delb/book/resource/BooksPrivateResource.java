package com.kreative.delb.book.resource;

import com.kreative.delb.book.dto.BookDto;
import com.kreative.delb.book.service.BooksFunctionnalService;
import com.kreative.delb.common.resource.AbstractRessourceApi;
import com.kreative.delb.common.resource.RestApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

import static com.kreative.delb.common.resource.constants.Api.PREFIXE;
import static com.kreative.delb.common.resource.constants.Api.PRIVATE;
import static com.kreative.delb.common.resource.constants.Api.Resource.BOOKS;

@RestController
@RequestMapping(PREFIXE + PRIVATE + BOOKS)
public class BooksPrivateResource extends AbstractRessourceApi<BookDto> implements RestApi<BookDto> {

	@Autowired
	private BooksFunctionnalService booksFunctionnalService;

	@Override
	public ResponseEntity create(@RequestBody BookDto objectDto) {
		BookDto bookDtoCreated = booksFunctionnalService.createBook(objectDto);
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
	public ResponseEntity<List<HashMap<String, Object>>> findAllAndFilterAplly(List<String> filterList) {
		return null;
	}

	@Override
	public ResponseEntity<BookDto> findOneById(String id) {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

	@Override
	public ResponseEntity<HashMap<String, Object>> findOneByIdAndFilterApply(String id, List<String> filterList) {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

	@Override
	public ResponseEntity update(String id, BookDto objectDto) {
		return new ResponseEntity<>(HttpStatus.NOT_IMPLEMENTED);
	}

}

