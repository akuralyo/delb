package com.kreative.delb.technicalService;

import com.kreative.delb.model.Author;
import com.kreative.delb.objectMother.AuthorMother;
import com.kreative.delb.repository.AuthorRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class AuthorTechnicalServiceTest {

	@Mock
	private AuthorRepository authorRepository;

	@InjectMocks
	private AuthorTechnicalService authorTechnicalService;

	@Before
	public void setUp() {
		initMocks(this);
	}

	@Test
	public void findAll() {
		// Initiation des réponses
		when(authorRepository.findAll()).thenReturn(new AuthorMother().createAuthorList());
		// Appel
		List<Author> authorList = authorTechnicalService.findAll();
		// Vérification
		assertEquals(authorList.size(), new AuthorMother().createAuthorList().size());
	}

	@Test
	public void findOneById_ok() {
		// Initiation des réponses
		when(authorRepository.findById(Mockito.anyString()))
				.thenReturn(Optional.of(new AuthorMother().createAuthor(0)));
		// Appel
		Author author = authorTechnicalService.findOneById("id");
		// Vérification
		assertNotNull(author);
		assertNotNull(author.getId());
		assertEquals(author.getFirstName(), new AuthorMother().createAuthor(0).getFirstName());
		assertEquals(author.getLastName(), new AuthorMother().createAuthor(0).getLastName());
		assertEquals(author.getNickName(), new AuthorMother().createAuthor(0).getNickName());
		assertEquals(author.getBirthday(), new AuthorMother().createAuthor(0).getBirthday());
	}

	@Test
	public void findOneById_ko() {
		// Initiation des réponses
		when(authorRepository.findById(Mockito.anyString()))
				.thenReturn(Optional.empty());
		//
		Author author = authorTechnicalService.findOneById("id");
		// Vérification
		Assert.assertNull(author);
	}

	@Test
	public void createAuthor_from_model_ok() {
	}

	@Test
	public void createAuthor_from_dto_ok() {
	}
}
