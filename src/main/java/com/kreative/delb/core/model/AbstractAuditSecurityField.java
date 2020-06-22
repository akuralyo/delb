package com.kreative.delb.common.model;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

abstract public class AbstractAuditSecurityField {

	@CreatedDate
	private LocalDate createdAt;

	@CreatedBy
	private String createdBy;

	@LastModifiedDate
	private LocalDateTime modifiedAt;

	@LastModifiedBy
	private String modifiedBy;

	public LocalDate getCreatedAt() {
		return createdAt;
	}

	public AbstractAuditSecurityField setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
		return this;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public AbstractAuditSecurityField setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
		return this;
	}

	public LocalDateTime getModifiedAt() {
		return modifiedAt;
	}

	public AbstractAuditSecurityField setModifiedAt(LocalDateTime modifiedAt) {
		this.modifiedAt = modifiedAt;
		return this;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public AbstractAuditSecurityField setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
		return this;
	}
}
