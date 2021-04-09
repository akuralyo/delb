package com.kreative.delb.domain.service.author.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.kreative.delb.domain.service.book.model.Book;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Author {

  private String adresse;

  private LocalDate birthday;

  private List<Book> bookList = new ArrayList<>();

  private String firstName;

  private String idAuthor;

  private String lastName;

  private String nickName;
}
