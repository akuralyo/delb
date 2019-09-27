package com.kreative.delb.resource.dto;


import com.fasterxml.jackson.annotation.JsonView;
import com.kreative.delb.resource.GroupValidation;
import com.kreative.delb.resource.ViewsAuthor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AuthorDto {

	@JsonView(ViewsAuthor.ApiPublic.class)
	@Null(groups = GroupValidation.IPost.class)
	private String id;

	@JsonView(ViewsAuthor.ApiPublic.class)
	@NotBlank(groups = GroupValidation.IPost.class)
	private String firstName;

	@JsonView(ViewsAuthor.ApiPublic.class)
	@NotBlank(groups = GroupValidation.IPost.class)
	private String lastName;

	@JsonView(ViewsAuthor.ApiPublic.class)
	private String nickName;

	@JsonView(ViewsAuthor.ApiPublic.class)
	private LocalDate birthday;

	@JsonView(ViewsAuthor.ApiPrivate.class)
	private String adresse;

	@JsonView(ViewsAuthor.ApiPublic.class)
	private List<BookDto> bookDtoList = new ArrayList<>();

	public List<BookDto> getBookDtoList() {
		return bookDtoList;
	}

	public AuthorDto setBookDtoList(List<BookDto> bookDtoList) {
		this.bookDtoList = bookDtoList;
		return this;
	}

	public String getNickName() {
		return nickName;
	}

	public AuthorDto setNickName(String nickName) {
		this.nickName = nickName;
		return this;
	}

	public String getAdresse() {
		return adresse;
	}

	public AuthorDto setAdresse(String adresse) {
		this.adresse = adresse;
		return this;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public AuthorDto setBirthday(LocalDate birthday) {
		this.birthday = birthday;
		return this;
	}

	public String getId() {
		return id;
	}

	public AuthorDto setId(String id) {
		this.id = id;
		return this;
	}

	public String getFirstName() {
		return firstName;
	}

	public AuthorDto setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public String getLastName() {
		return lastName;
	}

	public AuthorDto setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}
}
