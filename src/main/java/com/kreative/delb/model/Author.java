package com.kreative.delb.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
public class Author extends AbstractAuditSecurityField implements Serializable {
	@Id
	private ObjectId id;

	private String firstName;

	private String lastName;

	private String nickname;

	public ObjectId getId() {
		return id;
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

	public String getNickname() {
		return nickname;
	}

	public Author setNickname(String nickname) {
		this.nickname = nickname;
		return this;
	}
}
