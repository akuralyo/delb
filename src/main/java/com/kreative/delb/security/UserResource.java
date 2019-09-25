package com.kreative.delb.security;

import com.kreative.delb.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.kreative.delb.resource.constants.Api.API;
import static com.kreative.delb.resource.constants.Api.PUBLIC;

@RestController
@RequestMapping(API + PUBLIC + "/users")
public class UserResource {

	@Autowired
	private UserService userService;

	@PostMapping
	public User createUser(@RequestBody User user) {
		User createdUser = userService.createUser(user);
		return createdUser;
	}
}
