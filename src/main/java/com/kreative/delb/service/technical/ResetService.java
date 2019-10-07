package com.kreative.delb.service.technical;

import com.kreative.delb.model.User;
import com.kreative.delb.repository.AuthorRepository;
import com.kreative.delb.repository.BookRepository;
import com.kreative.delb.security.Role;
import com.kreative.delb.security.RoleAuthority;
import com.kreative.delb.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;

@Service
public class ResetService {

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private UserRepository userRepository;

	public void init() {
		//
		authorRepository.deleteAll();
		bookRepository.deleteAll();
		userRepository.deleteAll();
		//
		createAdmin(0);
		//
		createAuthor(0);
		createAuthor(1);
	}

	private void createAdmin(int i) {
		createUser(Role.ADMIN, i);
	}

	private void createAuthor(int i) {
		createUser(Role.AUTHOR, i);
	}

	private void createUser(Role role, int i) {
		userRepository.save(new User()
				.setUsername(role.name() + i)
				.setPassword(role.name() + i)
				.setRoleAuthorityCollection(new ArrayList<>(
						Arrays.asList(new RoleAuthority().setRole(role)))));
	}
}
