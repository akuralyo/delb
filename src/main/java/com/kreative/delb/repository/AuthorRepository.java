package com.kreative.delb.repository;

import com.kreative.delb.model.Author;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AuthorRepository extends CrudRepository<Author, String> {

	List<Author> findAllByFirstName();

}
