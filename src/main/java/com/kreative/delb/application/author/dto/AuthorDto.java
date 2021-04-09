package com.kreative.delb.application.author.dto;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.kreative.delb.application.book.dto.BookDto;
import com.kreative.delb.application.common.ApiGroupValidation;
import com.kreative.delb.application.common.resource.ViewsAuthor;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorDto {

  @JsonView(ViewsAuthor.ApiPrivate.class)
  private String adresse;

  @JsonView(ViewsAuthor.ApiPublic.class)
  @JsonSerialize(using = ToStringSerializer.class)
  private LocalDate birthday;

  @JsonView(ViewsAuthor.ApiPublic.class)
  private List<BookDto> bookDtoList = new ArrayList<>();

  @JsonView(ViewsAuthor.ApiPublic.class)
  @NotBlank(groups = ApiGroupValidation.IPost.class)
  private String firstName;

  @JsonView(ViewsAuthor.ApiPublic.class)
  @Null(groups = ApiGroupValidation.IPost.class)
  private String idAuthor;

  @JsonView(ViewsAuthor.ApiPublic.class)
  @NotBlank(groups = ApiGroupValidation.IPost.class)
  private String lastName;

  @JsonView(ViewsAuthor.ApiPublic.class)
  private String nickName;
}
