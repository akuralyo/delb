package com.kreative.delb.mapper;

import com.kreative.delb.model.Author;
import com.kreative.delb.objectMother.AuthorMother;
import com.kreative.delb.resource.dto.AuthorDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
public class AuthorMapperTest {

	@Test
	public void mapToDto() {
		Author author = new AuthorMother().createAuthor(0);
		AuthorDto authorDto = new AuthorMapper().mapToDto(author);
		//
		assertNotNull(authorDto);
		assertEquals(authorDto.getId(), author.getId().toString());
		assertEquals(authorDto.getFirstName(), author.getFirstName());
		assertEquals(authorDto.getLastName(), author.getLastName());
		assertEquals(authorDto.getNickName(), author.getNickName());
		assertEquals(authorDto.getBirthday(), author.getBirthday());
	}

	@Test
	public void mapToModel() {
		AuthorDto authorDto = new AuthorMother().createAuthorDto(0);
		Author author = new AuthorMapper().mapToModel(authorDto);
		//
		assertNotNull(author);
		assertNull(author.getId());
		assertEquals(author.getFirstName(), authorDto.getFirstName());
		assertEquals(author.getLastName(), authorDto.getLastName());
		assertEquals(author.getNickName(), authorDto.getNickName());
		assertEquals(author.getBirthday(), authorDto.getBirthday());
	}
}
