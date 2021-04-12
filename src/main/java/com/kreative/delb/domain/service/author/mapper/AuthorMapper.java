package com.kreative.delb.domain.service.author.mapper;

import com.kreative.delb.application.common.utils.Transformer;
import com.kreative.delb.domain.service.author.model.Author;
import com.kreative.delb.infrastructure.h2.author.model.AuthorModel;
import org.springframework.stereotype.Component;

@Component
public class AuthorMapper implements Transformer<AuthorModel, Author> {

  @Override
  public Author mapToDto(final AuthorModel source) {
    if (source != null) {
      final Author authorDto = new Author();

      authorDto.setIdAuthor(source.getIdAuthor());
      authorDto.setFirstName(source.getFirstName());
      authorDto.setLastName(source.getLastName());
      authorDto.setNickName(source.getNickName());
      authorDto.setBirthday(source.getBirthday());
      authorDto.setAdresse(source.getAdresse());

      return authorDto;
    } else {
      return null;
    }
  }

  @Override
  public AuthorModel mapToModel(final Author source) {
    if (source != null) {
      final AuthorModel authorModel = new AuthorModel();

      authorModel.setFirstName(source.getFirstName());
      authorModel.setLastName(source.getLastName());
      authorModel.setNickName(source.getNickName());
      authorModel.setBirthday(source.getBirthday());
      authorModel.setAdresse(source.getAdresse());

      return authorModel;
    } else {
      return null;
    }
  }
}
