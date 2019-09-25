package com.kreative.delb.technicalService;

import com.kreative.delb.model.Author;
import com.kreative.delb.repository.AuthorRepository;
import org.bson.types.ObjectId;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class AuthorTechnicalServiceTest {

	@Mock
	private AuthorRepository authorRepository;

	@InjectMocks
	private AuthorTechnicalService authorTechnicalService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}


	@Test
	public void findAll() {
		// Initiation des réponses
		Mockito.when(authorRepository.findAll()).thenReturn(createAuthorList());
		// Appel
		List<Author> authorList = authorTechnicalService.findAll();
		// Vérification
		Assert.assertEquals(authorList.size(), createAuthorList().size());
	}

	@Test
	public void findOneById_ok() {
		// Initiation des réponses
		Mockito.when(authorRepository.findById(Mockito.anyString()))
				.thenReturn(Optional.of(createAuthor(0)));
		// Appel
		Author author = authorTechnicalService.findOneById("id");
		// Vérification
		Assert.assertNotNull(author);
		Assert.assertNotNull(author.getId());
		Assert.assertEquals(author.getFirstName(), createAuthor(0).getFirstName());
		Assert.assertEquals(author.getLastName(), createAuthor(0).getLastName());
		Assert.assertEquals(author.getNickname(), createAuthor(0).getNickname());
		Assert.assertEquals(author.getBirthday(), createAuthor(0).getBirthday());
	}

	@Test
	public void findOneById_ko() {
		// Initiation des réponses
		Mockito.when(authorRepository.findById(Mockito.anyString()))
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

	private List<Author> createAuthorList() {
		List<Author> authorList = new ArrayList<>();
		authorList.add(createAuthor(0));
		authorList.add(createAuthor(1));
		authorList.add(createAuthor(2));
		return authorList;
	}

	private Author createAuthor(int i) {
		return new Author()
				.setId(new ObjectId())
				.setFirstName("FirstName" + i)
				.setLastName("LastName" + i)
				.setNickname("NickName" + i);
	}
}
