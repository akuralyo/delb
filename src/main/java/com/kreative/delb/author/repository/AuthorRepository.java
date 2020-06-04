package com.kreative.delb.author.repository;

import com.kreative.delb.author.model.Author;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AuthorRepository extends CrudRepository<Author, String> {

	Optional<Author> findOneByFirstName(String firstName);

	Optional<Author> findOneByLastName(String lastName);

	Optional<Author> findOneByIdAuthor(String idAuthor);
}
