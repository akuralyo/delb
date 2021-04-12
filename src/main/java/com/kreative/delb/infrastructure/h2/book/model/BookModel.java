package com.kreative.delb.infrastructure.h2.book.model;

import com.kreative.delb.infrastructure.common.model.AbstractAuditSecurityField;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

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
