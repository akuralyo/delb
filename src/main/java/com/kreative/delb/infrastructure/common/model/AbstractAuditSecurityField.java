package com.kreative.delb.infrastructure.common.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public abstract class AbstractAuditSecurityField {

  @CreatedDate private LocalDate createdAt;

  @CreatedBy private String createdBy;

  @LastModifiedDate private LocalDateTime modifiedAt;

  @LastModifiedBy private String modifiedBy;
}
