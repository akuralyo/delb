package com.kreative.delb.resource.dto;


import com.fasterxml.jackson.annotation.JsonView;
import com.kreative.delb.resource.GroupValidation;
import com.kreative.delb.resource.Views;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AuthorDto {

	@JsonView(Views.Public.class)
	@Null(groups = GroupValidation.IPost.class)
	private String id;

	@JsonView(Views.Public.class)
	@NotBlank(groups = GroupValidation.IPost.class)
	private String firstName;

	@JsonView(Views.Public.class)
	@NotBlank(groups = GroupValidation.IPost.class)
	private String lastName;

	@JsonView(Views.Public.class)
	private String nickName;

	@JsonView(Views.Public.class)
	private LocalDate birthday;

	@JsonView(Views.Private.class)
	private String adresse;

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

	public String getAdresse() {
		return adresse;
	}

	public AuthorDto setNickName(String nickName) {
		this.nickName = nickName;
		return this;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public String getId() {
		return id;
	}

	public AuthorDto setId(String id) {
		this.id = id;
		return this;
	}

	public AuthorDto setBirthday(LocalDate birthday) {
		this.birthday = birthday;
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

	public AuthorDto setAdresse(String adresse) {
		this.adresse = adresse;
		return this;
	}
}
