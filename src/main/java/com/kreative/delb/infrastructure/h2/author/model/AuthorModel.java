package com.kreative.delb.infrastructure.h2.author.model;

import com.kreative.delb.infrastructure.common.model.AbstractAuditSecurityField;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "AUTHOR")
public class AuthorModel extends AbstractAuditSecurityField implements Serializable {
  @Value("ADRESSE")
  private String adresse;

  @Value("BIRTHDAY")
  private LocalDate birthday;

  @Value("FIRST_NAME")
  private String firstName;

  @Id
  @Value("ID_AUTHOR")
  private String idAuthor;

  @Value("LAST_NAME")
  private String lastName;

  @Value("NICK_NAME")
  private String nickName;

  @Value("USER_ID")
  private String userId;
}
