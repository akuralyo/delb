package com.kreative.delb.author.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kreative.delb.infrastructure.h2.author.model.AuthorModel;
import com.kreative.delb.infrastructure.h2.author.repository.AuthorRepository;

@Component
public class AuthorDAO {

  @Autowired private AuthorRepository authorRepository;

  public void deleteAll() {
    authorRepository.deleteAll();
  }

  public List<AuthorModel> findAll() {
    final List<AuthorModel> authors = new ArrayList<>();
    authorRepository.findAll().forEach(authors::add);
    return authors;
  }

  public AuthorModel findAnyone() {
    final List<AuthorModel> authorList = findAll();
    return authorList.get(new Random().nextInt(authorList.size()));
  }

  public AuthorModel findOne(final String id) {
    final Optional<AuthorModel> authorOptional = authorRepository.findById(id);
    return authorOptional.orElse(null);
  }
}
