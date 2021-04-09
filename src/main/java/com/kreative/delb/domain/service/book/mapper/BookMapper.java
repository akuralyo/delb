package com.kreative.delb.domain.service.book.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kreative.delb.application.common.utils.Transformer;
import com.kreative.delb.domain.service.author.mapper.AuthorMapper;
import com.kreative.delb.domain.service.author.model.Author;
import com.kreative.delb.domain.service.book.model.Book;
import com.kreative.delb.infrastructure.h2.book.model.BookModel;

@Component
public class BookMapper implements Transformer<BookModel, Book> {

  @Autowired private AuthorMapper authorMapper;

  @Override
  public Book mapToDto(final BookModel bookModel) {
    final Book book = new Book();

    book.setIdBook(bookModel.getIdBook());
    book.setTitle(bookModel.getTitle());

    final Author author = new Author();
    author.setIdAuthor(bookModel.getAuthorId());

    book.setAuthor(author);

    return book;
  }

  @Override
  public BookModel mapToModel(final Book source) {
    return null;
  }
}
