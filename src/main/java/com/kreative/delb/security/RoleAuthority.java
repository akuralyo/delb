package com.kreative.delb.security;

import org.springframework.security.core.GrantedAuthority;

public class RoleAuthority implements GrantedAuthority {

	private ConstantesRole constantesRole;

	@Override
	public String getAuthority() {
		return this.constantesRole.name();
	}

	public RoleAuthority setConstantesRole(ConstantesRole constantesRole) {
		this.constantesRole = constantesRole;
		return this;
	}
}
