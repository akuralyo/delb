package com.kreative.delb.resource;

import com.kreative.delb.functionalService.AuthorFunctionnalService;
import com.kreative.delb.resource.dto.AuthorDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.kreative.delb.resource.constants.Api.*;

@RestController
@RequestMapping(API + PUBLIC + AUTHOR)
public class AuthorsResource {

	@Autowired
	private AuthorFunctionnalService authorFunctionnalService;

	@GetMapping
	public List<AuthorDto> getAll() {
		return authorFunctionnalService.findAll();
	}
}
