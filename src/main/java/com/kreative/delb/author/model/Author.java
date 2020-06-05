package com.kreative.delb.author.model;

import com.kreative.delb.common.model.AbstractAuditSecurityField;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Table(name = "AUTHOR")
public class Author extends AbstractAuditSecurityField implements Serializable {

	@Id
	@Value("ID_AUTHOR")
	private String idAuthor;

	@Value("FIRST_NAME")
	private String firstName;

	@Value("LAST_NAME")
	private String lastName;

	@Value("NICK_NAME")
	private String nickName;

	@Value("USER_ID")
	private String userId;

	@Value("BIRTHDAY")
	private LocalDate birthday;

	@Value("ADRESSE")
	private String adresse;

	public String getUserId() {
		return userId;
	}

	public Author setUserId(String userId) {
		this.userId = userId;
		return this;
	}

	public String getIdAuthor() {
		return idAuthor;
	}

	public Author setIdAuthor(String idAuthor) {
		this.idAuthor = idAuthor;
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
