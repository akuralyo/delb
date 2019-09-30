package com.kreative.delb.security;

import com.kreative.delb.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.kreative.delb.resource.constants.Api.PREFIXE;
import static com.kreative.delb.resource.constants.Api.PUBLIC;
import static com.kreative.delb.resource.constants.Api.PathVariable.PV_SELF;
import static com.kreative.delb.resource.constants.Api.Resource.USERS;

@RestController
@RequestMapping(PREFIXE + PUBLIC + USERS)
public class UserResource {

	@Autowired
	private UserService userService;

	@PostMapping
	public User createUser(@RequestBody User user) {
		return userService.createUser(user);
	}

	@GetMapping(PV_SELF)
	public ResponseEntity getSelf() {
		return new ResponseEntity(HttpStatus.NOT_IMPLEMENTED);
	}
}
