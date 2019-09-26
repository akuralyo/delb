package com.kreative.delb.resource.author;

import com.fasterxml.jackson.annotation.JsonView;
import com.kreative.delb.resource.ViewsAuthor;
import com.kreative.delb.resource.dto.AuthorDto;
import com.kreative.delb.service.functionnal.AuthorFunctionnalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.kreative.delb.resource.constants.Api.*;

@RestController
@RequestMapping(API + PUBLIC + AUTHORS)
public class AuthorsPublicResource {

	@Autowired
	private AuthorFunctionnalService authorFunctionnalService;

	@JsonView(ViewsAuthor.ApiPublic.class)
	@GetMapping
	public List<AuthorDto> findAll() {
		return authorFunctionnalService.findAll();
	}

	@GetMapping(PV_ID)
	public ResponseEntity get(@PathVariable String id) {
		AuthorDto authorDto = authorFunctionnalService.findOneById(id);
		return new ResponseEntity<>(authorDto, HttpStatus.OK);
	}
}
