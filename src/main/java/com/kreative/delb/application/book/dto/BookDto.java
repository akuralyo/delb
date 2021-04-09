package com.kreative.delb.application.book.dto;

import com.fasterxml.jackson.annotation.JsonView;
import com.kreative.delb.application.author.dto.AuthorDto;
import com.kreative.delb.application.common.resource.ViewsAuthor;

public class BookDto {

  @JsonView(ViewsAuthor.ApiPublic.class)
  private AuthorDto authorDto = new AuthorDto();

  @JsonView(ViewsAuthor.ApiPublic.class)
  private String idBook;

  @JsonView(ViewsAuthor.ApiPublic.class)
  private String title;

  public AuthorDto getAuthorDto() {
    return authorDto;
  }

  public String getIdBook() {
    return idBook;
  }

  public String getTitle() {
    return title;
  }

  public BookDto setAuthorDto(AuthorDto authorDto) {
    this.authorDto = authorDto;
    return this;
  }

  public BookDto setIdBook(String idBook) {
    this.idBook = idBook;
    return this;
  }

  public BookDto setTitle(String title) {
    this.title = title;
    return this;
  }
}
