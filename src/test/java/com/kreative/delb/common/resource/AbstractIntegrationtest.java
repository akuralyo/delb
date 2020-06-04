package com.kreative.delb.common.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kreative.delb.author.dao.AuthorDAO;
import com.kreative.delb.author.model.Author;
import com.kreative.delb.author.objectMother.AuthorMother;
import com.kreative.delb.author.repository.AuthorRepository;
import com.kreative.delb.book.dao.BookDAO;
import com.kreative.delb.book.objectMother.BookMother;
import com.kreative.delb.book.repository.BookRepository;
import com.kreative.delb.security.UserRepository;
import com.kreative.delb.user.User;
import com.kreative.delb.user.dao.UserDAO;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.UUID;

public abstract class AbstractIntegrationtest {

	@Autowired
	protected AuthorDAO authorDAO;

	@Autowired
	protected UserDAO userDAO;

	@Autowired
	protected BookDAO bookDAO;

	@Autowired
	protected MockMvc mockMvc;

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private UserRepository userRepository;

	public void initDbAuthor(int max) {
		for (int i = 0; i < max; i++) {
			Author author = authorRepository.save(new AuthorMother().createAuthor(i));
			bookRepository.saveAll(new BookMother().createBookList(author.getIdAuthor()));
		}
	}

	private User createUser(int i) {
		return userRepository.save(new User()
				.setIdUser(UUID.randomUUID().toString())
				.setUsername("Username" + i)
				.setPassword("Password" + i));
	}

	protected void createAuthor(int i) {
		Author author = authorRepository.save(new AuthorMother().createAuthor(i));
		bookRepository.saveAll(new BookMother().createBookList(author.getIdAuthor()));
	}

	@Before
	protected void before() throws Exception {
		userRepository.deleteAll();
		authorRepository.deleteAll();
		bookRepository.deleteAll();
		//
		initDbAuthor(5);
		createUser(0);
		createUser(1);
	}

	public static ResultMatcher allOf(final ResultMatcher... matchers) {
		return (result) -> {
			for (ResultMatcher m : matchers) {
				m.match(result);
			}
		};
	}

	public static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
