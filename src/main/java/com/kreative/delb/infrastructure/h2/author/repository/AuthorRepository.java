package com.kreative.delb.infrastructure.h2.author.repository;

import com.kreative.delb.infrastructure.h2.author.model.AuthorModel;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface AuthorRepository extends CrudRepository<AuthorModel, String> {

  Optional<AuthorModel> findOneByFirstName(String firstName);

  Optional<AuthorModel> findOneByIdAuthor(String idAuthor);

  Optional<AuthorModel> findOneByLastName(String lastName);
}
