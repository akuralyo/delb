package com.kreative.delb.resource;

import com.kreative.delb.functionalService.AuthorFunctionnalService;
import com.kreative.delb.resource.dto.AuthorDto;
import com.kreative.delb.resource.dto.BookDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

import static com.kreative.delb.resource.constants.Api.*;

@RestController
@RequestMapping(API)
public class BooksResource {

	@RequestMapping(PUBLIC + BOOKS)
	public class BooksPublicResource {
		@GetMapping
		public List<BookDto> getAll() {
			return new ArrayList<>();
		}

		@GetMapping
		public List<BookDto> getAllByPredicate() {
			return new ArrayList<>();
		}

		@GetMapping(SEARCH_BY_ID)
		public BookDto get(@PathVariable String id) {
			return new BookDto();
		}

		@GetMapping(AUTHOR)
		public AuthorDto getAuthor() {
			return new AuthorDto();
		}
	}

	@RequestMapping(PRIVATE + BOOKS)
	public class BooksPrivateResource {
		@PostMapping
		public HttpStatus create(BookDto bookDto) {
			return HttpStatus.CREATED;
		}

		@RequestMapping(SEARCH_BY_ID)
		public class BookResource {
			@PutMapping
			public HttpStatus update(BookDto bookDto) {
				return HttpStatus.OK;
			}

			@RequestMapping(AUTHOR)
			public class AuthorRessource {
				@PutMapping
				public HttpStatus updateAuthor(AuthorDto authorDto) {
					return HttpStatus.OK;
				}
			}
		}
	}
}


