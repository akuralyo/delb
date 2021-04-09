package com.kreative.delb.infrastructure.h2.author.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.kreative.delb.infrastructure.h2.author.model.AuthorModel;

public interface AuthorRepository extends CrudRepository<AuthorModel, String> {

  Optional<AuthorModel> findOneByFirstName(String firstName);

  Optional<AuthorModel> findOneByIdAuthor(String idAuthor);

  Optional<AuthorModel> findOneByLastName(String lastName);
}
