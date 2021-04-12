package com.kreative.delb.application.author.adapter;

import com.kreative.delb.application.author.dto.AuthorDto;
import com.kreative.delb.domain.api.AuthorApi;
import com.kreative.delb.domain.service.author.model.Author;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Slf4j
public class AuthorDtoAdapter implements AuthorApi {

  @Override
  public void deleteAuthor(String id) {}

  @Override
  public List<AuthorDto> findAll() {
    return null;
  }

  @Override
  public AuthorDto findOneById(String id) {
    return null;
  }

  @Override
  public AuthorDto updateAuthorById(String id, AuthorDto author) {
    return null;
  }
}
