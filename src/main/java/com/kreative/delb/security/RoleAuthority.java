package com.kreative.delb.security;

import org.springframework.security.core.GrantedAuthority;

public class RoleAuthority implements GrantedAuthority {

	private Role role;

	@Override
	public String getAuthority() {
		return this.role.name();
	}

	public RoleAuthority setRole(Role role) {
		this.role = role;
		return this;
	}
}
