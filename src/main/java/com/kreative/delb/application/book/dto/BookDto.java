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

  public BookDto setAuthorDto(AuthorDto authorDto) {
    this.authorDto = authorDto;
    return this;
  }

  public String getIdBook() {
    return idBook;
  }

  public BookDto setIdBook(String idBook) {
    this.idBook = idBook;
    return this;
  }

  public String getTitle() {
    return title;
  }

  public BookDto setTitle(String title) {
    this.title = title;
    return this;
  }
}
