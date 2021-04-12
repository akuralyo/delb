package com.kreative.delb.infrastructure.h2.author.service;

import com.kreative.delb.domain.service.author.model.Author;
import com.kreative.delb.domain.spi.AuthorSpi;
import com.kreative.delb.infrastructure.h2.author.model.AuthorModel;
import com.kreative.delb.infrastructure.h2.author.repository.AuthorRepository;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class AuthorAdapter implements AuthorSpi {

  private static final Logger LOGGER = Logger.getLogger(AuthorAdapter.class);

  private final AuthorRepository authorRepository;

  public AuthorAdapter(final AuthorRepository authorRepository) {
    this.authorRepository = authorRepository;
  }

  @Override
  public AuthorModel createAuthor(final Author author) {
    return authorRepository.save(createOrUpdateAuthor(UUID.randomUUID().toString(), author));
  }

  @Override
  public AuthorModel createAuthor(final AuthorModel author) {
    return authorRepository.save(author);
  }

  @Override
  public void deleteAuthor(final String id) {
    authorRepository.deleteById(id);
  }

  @Override
  public List<AuthorModel> findAll() {
    LOGGER.debug("Accès à la méthode");
    final List<AuthorModel> authors = new ArrayList<>();
    authorRepository.findAll().forEach(authors::add);
    LOGGER.debug("Nb d'éléments : " + authors.size());
    return authors;
  }

  @Override
  public Optional<AuthorModel> findById(final String id) {
    return authorRepository.findById(id);
  }

  @Override
  public AuthorModel findOneById(final String idAuthor) {
    final Optional<AuthorModel> authorOptional = authorRepository.findOneByIdAuthor(idAuthor);
    return authorOptional.orElse(null);
  }

  @Override
  public AuthorModel updateById(final String id, final Author author) {
    final Optional<AuthorModel> authorOptional = authorRepository.findById(id);
    if (authorOptional.isPresent()) {
      return authorRepository.save(
          createOrUpdateAuthor(authorOptional.get().getIdAuthor(), author));
    } else {
      LOGGER.warn("Auteur not found");
      return null;
    }
  }

  @Override
  public AuthorModel updateByUsername(final String username, final Author author) {
    final Optional<AuthorModel> authorOptional = authorRepository.findOneByIdAuthor(username);
    if (authorOptional.isPresent()) {
      return authorRepository.save(
          createOrUpdateAuthor(authorOptional.get().getIdAuthor(), author));
    } else {
      LOGGER.warn("Auteur not found");
      return null;
    }
  }

  private AuthorModel createOrUpdateAuthor(final String IdAuthor, final Author author) {
    final AuthorModel authorModel = new AuthorModel();

    authorModel.setIdAuthor(IdAuthor);
    authorModel.setFirstName(author.getFirstName());
    authorModel.setLastName(author.getLastName());
    authorModel.setNickName(author.getNickName());
    authorModel.setBirthday(author.getBirthday());
    authorModel.setAdresse(author.getAdresse());

    return authorModel;
  }
}
