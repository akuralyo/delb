package com.kreative.delb.common.resource;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.kreative.delb.author.dao.AuthorDAO;
import com.kreative.delb.author.model.Author;
import com.kreative.delb.author.objectMother.AuthorMother;
import com.kreative.delb.author.repository.AuthorRepository;
import com.kreative.delb.book.dao.BookDAO;
import com.kreative.delb.book.objectMother.BookMother;
import com.kreative.delb.book.repository.BookRepository;
import com.kreative.delb.security.ConstantesRole;
import com.kreative.delb.security.MemberRepository;
import com.kreative.delb.security.RoleRepository;
import com.kreative.delb.user.Member;
import com.kreative.delb.user.Role;
import com.kreative.delb.user.dao.MemberDAO;
import com.kreative.delb.user.dao.RoleDAO;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultMatcher;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;

public abstract class AbstractIntegrationtest {

	private static final Logger LOGGER = Logger.getLogger(AbstractIntegrationtest.class);

	private static final ObjectMapper MAPPER = new ObjectMapper()
			.configure(WRITE_DATES_AS_TIMESTAMPS, false)
			.configure(FAIL_ON_UNKNOWN_PROPERTIES, false)
			.registerModule(new JavaTimeModule());

	@Autowired
	protected AuthorDAO authorDAO;

	@Autowired
	protected MemberDAO memberDAO;

	@Autowired
	protected BookDAO bookDAO;

	@Autowired
	protected MockMvc mockMvc;

	@Autowired
	private AuthorRepository authorRepository;

	@Autowired
	private BookRepository bookRepository;

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private RoleDAO roleDAO;

	@Before
	public void before() throws Exception {
		memberRepository.deleteAll();
		authorRepository.deleteAll();
		bookRepository.deleteAll();
		//
		initDbAuthor(5);
		//
		List<Member> memberList = memberDAO.findAll();
		List<Role> roleList = roleDAO.findAll();
		//
		LOGGER.debug("Nb de role : " + roleList.size());
		LOGGER.debug("Nb de membres : " + memberList.size());
		LOGGER.debug("Nb Author : " + authorDAO.findAll().size());
		LOGGER.debug("Nb Book : " + bookDAO.findAll().size());
	}

	private void createAuthor(int i) {
		Member user = createUser(i, ConstantesRole.ROLE_AUTHOR.name());
		Author author = authorRepository.save(new AuthorMother().createAuthor(i, user.getIdUser()));
		bookRepository.saveAll(new BookMother().createBookList(author.getIdAuthor()));
	}

	private Member createUser(int i, String roleLabel) {
		String idMember = UUID.randomUUID().toString();
		Member member = new Member()
				.setIdUser(idMember)
				.setUsername("Username " + roleLabel + " N°" + i)
				.setPassword("Password " + roleLabel + " N°" + i);
		/**
		 Role role1 = new Role()
		 .setId(UUID.randomUUID().toString())
		 .setMember(member)
		 .setRoleName(roleLabel);

		 member.getRoles().add(role1);
		 */
		return memberRepository.save(member);
	}

	private void initDbAuthor(int max) {
		for (int i = 0; i < max; i++) {
			createAuthor(i);
		}
	}

	protected static ResultMatcher allOf(final ResultMatcher... matchers) {
		return (result) -> {
			for (ResultMatcher m : matchers) {
				m.match(result);
			}
		};
	}

	protected static String asJsonString(final Object obj) {
		try {
			return new ObjectMapper().writeValueAsString(obj);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	protected static <T> T parseResponse(MvcResult result, Class<T> responseClass) {
		try {
			String contentAsString = result.getResponse().getContentAsString();
			return MAPPER.readValue(contentAsString, responseClass);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
