package com.kreative.delb.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;

@Document
public class Author extends AbstractAuditSecurityField implements Serializable {
	@Id
	private ObjectId id;

	private String firstName;

	private String lastName;

	private String nickName;

	private LocalDate birthday;

	private String adresse;

	public ObjectId getId() {
		return id;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public String getAdresse() {
		return adresse;
	}

	public Author setId(ObjectId id) {
		this.id = id;
		return this;
	}

	public String getFirstName() {
		return firstName;
	}

	public Author setFirstName(String firstName) {
		this.firstName = firstName;
		return this;
	}

	public String getLastName() {
		return lastName;
	}

	public Author setLastName(String lastName) {
		this.lastName = lastName;
		return this;
	}

	public String getNickName() {
		return nickName;
	}

	public Author setNickName(String nickName) {
		this.nickName = nickName;
		return this;
	}

	public Author setBirthday(LocalDate birthday) {
		this.birthday = birthday;
		return this;
	}

	public Author setAdresse(String adresse) {
		this.adresse = adresse;
		return this;
	}
}
