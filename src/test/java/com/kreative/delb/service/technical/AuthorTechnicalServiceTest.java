package com.kreative.delb.service.technical;

import com.kreative.delb.model.Author;
import com.kreative.delb.objectMother.AuthorMother;
import com.kreative.delb.repository.AuthorRepository;
import com.kreative.delb.resource.dto.AuthorDto;
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

	@Test
	public void findAll() {
		// Initiation des réponses
		when(authorRepository.findAll()).thenReturn(new AuthorMother().createAuthorList());
		// Appel
		List<Author> authorList = authorTechnicalService.findAll();
		// Vérification
		assertEquals(new AuthorMother().createAuthorList().size(), authorList.size());
		checkObject_author(authorList.get(1), new AuthorMother().createAuthor(1));
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
	public void findOneById_ok() {
		// Initiation des réponses
		when(authorRepository.findById(Mockito.anyString()))
				.thenReturn(Optional.of(new AuthorMother().createAuthor(0)));
		// Appel
		Author author = authorTechnicalService.findOneById("id");
		// Vérification
		checkObject_author(author, new AuthorMother().createAuthor(0));
	}

	@Before
	public void setUp() {
		initMocks(this);
	}

	@Test
	public void update_ko() {
		when(authorRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());
		//
		Author author = authorTechnicalService.update("id", new AuthorDto());
		//
		Assert.assertNull(author);
	}

	@Test
	public void update_ok() {
		when(authorRepository.findById(Mockito.anyString())).thenReturn(
				Optional.of(new AuthorMother().createAuthor(0)));
		Author author = new AuthorMother().createAuthor(0).setFirstName("MyFirstName");
		AuthorDto authorDto = new AuthorMother().createAuthorDto(0).setFirstName("MyFirstName");
		when(authorRepository.save(Mockito.any())).thenReturn(author);
		//
		Author actualResponse = authorTechnicalService.update(author.getId().toString(), authorDto);
		//
		Assert.assertEquals(author, actualResponse);
	}

	private void checkObject_author(Author expected, Author returned) {
		assertNotNull(returned);
		assertNotNull(returned.getId());
		assertEquals(returned.getFirstName(), expected.getFirstName());
		assertEquals(returned.getLastName(), expected.getLastName());
		assertEquals(returned.getNickName(), expected.getNickName());
		assertEquals(returned.getBirthday(), expected.getBirthday());
	}
}
