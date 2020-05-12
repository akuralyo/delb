package com.kreative.delb.book.model;

import com.kreative.delb.common.model.AbstractAuditSecurityField;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

@Document
public class Book extends AbstractAuditSecurityField implements Serializable {

	@Id
	private ObjectId id;

	private String name;

	private ObjectId authorId;

	public ObjectId getAuthorId() {
		return authorId;
	}

	public Book setAuthorId(ObjectId authorId) {
		this.authorId = authorId;
		return this;
	}

	public ObjectId getId() {
		return id;
	}

	public Book setId(ObjectId id) {
		this.id = id;
		return this;
	}

	public String getName() {
		return name;
	}

	public Book setName(String name) {
		this.name = name;
		return this;
	}
}
