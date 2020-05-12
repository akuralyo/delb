package com.kreative.delb.user;

import com.kreative.delb.common.model.AbstractAuditSecurityField;
import com.kreative.delb.security.RoleAuthority;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serializable;
import java.util.Collection;


@Document
public class User extends AbstractAuditSecurityField implements Serializable, UserDetails {

	@Id
	private ObjectId id;

	private String username;

	private String password;

	private Collection<RoleAuthority> roleAuthorityCollection;

	public ObjectId getUserId() {
		return id;
	}

	public User setUserId(ObjectId userId) {
		this.id = userId;
		return this;
	}

	public Collection<RoleAuthority> getRoleAuthorityCollection() {
		return roleAuthorityCollection;
	}

	public User setRoleAuthorityCollection(Collection<RoleAuthority> roleAuthorityCollection) {
		this.roleAuthorityCollection = roleAuthorityCollection;
		return this;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return this.roleAuthorityCollection;
	}

	@Override
	public String getPassword() {
		return password;
	}

	public User setPassword(String password) {
		this.password = password;
		return this;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public User setUsername(String username) {
		this.username = username;
		return this;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}
}
