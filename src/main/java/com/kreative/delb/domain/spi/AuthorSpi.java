package com.kreative.delb.domain.spi;

import com.kreative.delb.domain.service.author.model.Author;
import com.kreative.delb.infrastructure.h2.author.model.AuthorModel;

import java.util.List;
import java.util.Optional;

public interface AuthorSpi {

  AuthorModel createAuthor(Author authorDto);

  AuthorModel createAuthor(AuthorModel author);

  void deleteAuthor(String id);

  List<AuthorModel> findAll();

  Optional<AuthorModel> findById(String id);

  AuthorModel findOneById(String idAuthor);

  AuthorModel updateById(String id, Author author);

  AuthorModel updateByUsername(String username, Author author);
}
