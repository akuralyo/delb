package com.kreative.delb.service.technical;

import com.kreative.delb.model.User;
import com.kreative.delb.repository.AuthorRepository;
import com.kreative.delb.repository.BookRepository;
import com.kreative.delb.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
		userRepository.save(new User().setUsername("ADMIN").setPassword("ADMIN"));
	}
}
