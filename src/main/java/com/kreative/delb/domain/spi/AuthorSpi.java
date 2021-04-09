package com.kreative.delb.domain.spi;

import java.util.List;
import java.util.Optional;

import com.kreative.delb.domain.service.author.model.Author;
import com.kreative.delb.infrastructure.h2.author.model.AuthorModel;

public interface AuthorSpi {

  AuthorModel createAuthor(Author authorDto);

  AuthorModel createAuthor(AuthorModel author);

  void deleteAuthor(String id);

  List<AuthorModel> findAll();

  AuthorModel findOneById(String idAuthor);

  AuthorModel updateById(String id, Author author);

  AuthorModel updateByUsername(String username, Author author);

  Optional<AuthorModel> findById(String id);
}
