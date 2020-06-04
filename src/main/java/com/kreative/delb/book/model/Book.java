package com.kreative.delb.book.model;

import com.kreative.delb.common.model.AbstractAuditSecurityField;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "BOOK")
public class Book extends AbstractAuditSecurityField implements Serializable {

	@Id
	@Value("ID_BOOK")
	private String idBook;

	@Value("TITLE")
	private String title;

	@Value("AUTHOR_ID")
	private String authorId;

	public String getAuthorId() {
		return authorId;
	}

	public Book setAuthorId(String authorId) {
		this.authorId = authorId;
		return this;
	}

	public String getIdBook() {
		return idBook;
	}

	public Book setIdBook(String idBook) {
		this.idBook = idBook;
		return this;
	}

	public String getTitle() {
		return title;
	}

	public Book setTitle(String title) {
		this.title = title;
		return this;
	}
}
