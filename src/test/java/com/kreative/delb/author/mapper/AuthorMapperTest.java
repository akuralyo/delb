package com.kreative.delb.author.mapper;

import com.kreative.delb.author.dto.AuthorDto;
import com.kreative.delb.author.model.Author;
import com.kreative.delb.author.objectMother.AuthorMother;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class AuthorMapperTest {
	@Test
	public void mapToDto() {
		Author author = new AuthorMother().createAuthor(0);
		AuthorDto authorDto = new AuthorMapper().mapToDto(author);
		//
		assertNotNull(authorDto);
		assertEquals(authorDto.getIdAuthor(), author.getIdAuthor());
		assertEquals(authorDto.getFirstName(), author.getFirstName());
		assertEquals(authorDto.getLastName(), author.getLastName());
		assertEquals(authorDto.getNickName(), author.getNickName());
		assertEquals(authorDto.getBirthday(), author.getBirthday());
	}
}
