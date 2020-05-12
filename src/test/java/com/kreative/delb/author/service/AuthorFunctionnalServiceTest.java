package com.kreative.delb.author.service;

import com.kreative.delb.author.mapper.AuthorMapper;
import com.kreative.delb.author.service.AuthorFunctionnalService;
import com.kreative.delb.author.service.AuthorTechnicalService;
import com.kreative.delb.book.mapper.BookMapper;
import com.kreative.delb.book.service.BookTechnicalService;
import com.kreative.delb.common.resource.dto.AuthorDto;
import com.kreative.delb.author.objectMother.AuthorMother;
import com.kreative.delb.book.objectMother.BookMother;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.HttpClientErrorException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@RunWith(MockitoJUnitRunner.class)
public class AuthorFunctionnalServiceTest {

	@Mock
	private AuthorTechnicalService authorTechnicalService;

	@Mock
	private BookTechnicalService bookTechnicalService;

	@Mock
	private AuthorMapper authorMapper;

	@Mock
	private BookMapper bookMapper;

	@InjectMocks
	private AuthorFunctionnalService authorFunctionnalService;

	@Test
	public void findOneById_ok() {
		// Initiation des réponses
		when(authorTechnicalService.findOneById(Mockito.anyString()))
				.thenReturn(new AuthorMother().createAuthor(0));
		when(bookTechnicalService.findAllByAuthorId(Mockito.anyString()))
				.thenReturn(new BookMother().createBookList());
		// Appel
		AuthorDto authorDto = authorFunctionnalService.findOneById("id");
		// Vérification
		assertNotNull(authorDto);
		assertEquals(authorDto.getBookDtoList().size(), new BookMother().createBookList().size());
	}

	@Before
	public void setUp() {
		initMocks(this);
		when(authorMapper.mapToDto(Mockito.any()))
				.thenCallRealMethod();
		when(bookMapper.mapToDto(Mockito.any()))
				.thenCallRealMethod();
	}

	@Test(expected = HttpClientErrorException.class)
	public void updateAuthor_ko() {
		// Initiation des réponses
		when(authorTechnicalService.updateById(Mockito.anyString(), Mockito.any()))
				.thenReturn(null);
		// Appel
		authorFunctionnalService.updateAuthorById("id", new AuthorDto());
	}
}