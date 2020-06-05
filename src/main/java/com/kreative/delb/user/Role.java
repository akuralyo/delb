package com.kreative.delb.user;

import javax.persistence.*;

@Entity
@Table(name = "ROLE")
public class Role {

	@Id
	private String id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_MEMBER")
	private Member member;

	@Column(name = "ROLE_NAME")
	private String roleName;

	public Member getMember() {
		return member;
	}

	public Role setMember(Member member) {
		this.member = member;
		return this;
	}

	public String getId() {
		return id;
	}

	public Role setId(String id) {
		this.id = id;
		return this;
	}


	public String getRoleName() {
		return roleName;
	}

	public Role setRoleName(String roleName) {
		this.roleName = roleName;
		return this;
	}
}