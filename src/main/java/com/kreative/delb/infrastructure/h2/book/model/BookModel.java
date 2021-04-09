package com.kreative.delb.infrastructure.h2.book.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.beans.factory.annotation.Value;

import com.kreative.delb.infrastructure.common.model.AbstractAuditSecurityField;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "BOOK")
public class BookModel extends AbstractAuditSecurityField implements Serializable {

  @Value("AUTHOR_ID")
  private String authorId;

  @Id
  @Value("ID_BOOK")
  private String idBook;

  @Value("TITLE")
  private String title;
}
