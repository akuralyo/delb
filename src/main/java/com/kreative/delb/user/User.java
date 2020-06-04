package com.kreative.delb.user;

import com.kreative.delb.common.model.AbstractAuditSecurityField;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "USER")
public class User extends AbstractAuditSecurityField implements Serializable, UserDetails {

	@Id
	@Value("ID_USER")
	private String idUser;

	@Value("USERNAME")
	private String username;

	@Value("PASSWORD")
	private String password;

	public String getIdUser() {
		return idUser;
	}

	public User setIdUser(String userId) {
		this.idUser = userId;
		return this;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
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
