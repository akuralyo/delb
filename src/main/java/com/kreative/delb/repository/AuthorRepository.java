package com.kreative.delb.repository;

import com.kreative.delb.model.Author;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public interface AuthorRepository extends CrudRepository<Author, String>, QuerydslPredicateExecutor<Author> {

	List<Author> findAllByFirstName(Predicate<Author> predicate);

	Optional<Author> findOneByFirstName(String firstName);

	Optional<Author> findOneByLastName(String lastName);
}
