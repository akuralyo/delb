package com.kreative.delb.author.model;

import com.kreative.delb.common.model.AbstractAuditSecurityField;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;

@Document
public class Author extends AbstractAuditSecurityField implements Serializable {
	@Id
	private ObjectId id;

	private ObjectId userId;

	private String firstName;

	private String lastName;

	private String nickName;

	private LocalDate birthday;

	private String adresse;

	public ObjectId getUserId() {
		return userId;
	}

	public Author setUserId(ObjectId userId) {
		this.userId = userId;
		return this;
	}

	public ObjectId getId() {
		return id;
	}

	public Author setId(ObjectId id) {
		this.id = id;
		return this;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public Author setBirthday(LocalDate birthday) {
		this.birthday = birthday;
		return this;
	}

	public String getAdresse() {
		return adresse;
	}

	public Author setAdresse(String adresse) {
		this.adresse = adresse;
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
}
