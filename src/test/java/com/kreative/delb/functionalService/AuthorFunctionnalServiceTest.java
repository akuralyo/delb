package com.kreative.delb.functionalService;

import com.kreative.delb.resource.dto.AuthorDto;
import com.kreative.delb.technicalService.AuthorTechnicalService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.client.HttpClientErrorException;

@RunWith(MockitoJUnitRunner.class)
public class AuthorFunctionnalServiceTest {

	@Mock
	private AuthorTechnicalService authorTechnicalService;

	@InjectMocks
	private AuthorFunctionnalService authorFunctionnalService;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test(expected = HttpClientErrorException.class)
	public void updateAuthor_ko() {
		// Initiation des réponses
		Mockito.when(authorTechnicalService.updateAuthor(Mockito.anyString(), Mockito.any()))
				.thenReturn(null);
		// Appel
		authorFunctionnalService.updateAuthor("id", new AuthorDto());
	}
}