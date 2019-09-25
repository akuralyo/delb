package com.kreative.delb.resource.dto;

import java.time.LocalDate;

public class AuthorDto {

	private String id;

	private String firstName;

	private String lastName;

	private String nickName;

	private LocalDate birthday;

	public String getNickName() {
		return nickName;
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
}
