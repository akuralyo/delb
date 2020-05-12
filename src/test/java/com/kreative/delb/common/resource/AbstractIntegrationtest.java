package com.kreative.delb.common.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.kreative.delb.author.dao.AuthorDAO;
import com.kreative.delb.author.model.Author;
import com.kreative.delb.author.repository.AuthorRepository;
import com.kreative.delb.book.dao.BookDAO;
import com.kreative.delb.book.repository.BookRepository;
import com.kreative.delb.user.dao.UserDAO;
import com.kreative.delb.author.objectMother.AuthorMother;
import com.kreative.delb.book.objectMother.BookMother;
import com.kreative.delb.security.Role;
import com.kreative.delb.security.RoleAuthority;
import com.kreative.delb.security.UserRepository;
import com.kreative.delb.user.User;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.ArrayList;
import java.util.Arrays;

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
			User user = createUser(Role.AUTHOR, i);
			Author author = authorRepository.save(new AuthorMother().createAuthor(i, user.getUserId()));
			bookRepository.saveAll(new BookMother().createBookList(author.getId()));
		}
	}

	private User createUser(Role role, int i) {
		return userRepository.save(new User()
				.setUsername(role.name() + i)
				.setPassword(role.name() + i)
				.setRoleAuthorityCollection(new ArrayList<>(
						Arrays.asList(new RoleAuthority().setRole(role)))));
	}

	protected void createAdmin(int i) {
		createUser(Role.ADMIN, i);
	}

	protected void createAuthor(int i) {
		User user = createUser(Role.AUTHOR, i);
		Author author = authorRepository.save(new AuthorMother().createAuthor(i, user.getUserId()));
		bookRepository.saveAll(new BookMother().createBookList(author.getId()));
	}

	@Before
	protected void before() throws Exception {
		userRepository.deleteAll();
		authorRepository.deleteAll();
		bookRepository.deleteAll();
		//
		createAdmin(0);
		//
		initDbAuthor(5);
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
