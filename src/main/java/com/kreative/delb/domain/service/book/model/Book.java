package com.kreative.delb.domain.service.book.model;

import com.kreative.delb.domain.service.author.model.Author;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Book {

  private Author author = new Author();

  private String idBook;

  private String title;
}
