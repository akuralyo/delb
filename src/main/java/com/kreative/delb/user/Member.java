package com.kreative.delb.user;

import com.kreative.delb.common.model.AbstractAuditSecurityField;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Table(name = "MEMBER")
public class Member extends AbstractAuditSecurityField implements Serializable, UserDetails {

	@Id
	@Column(name = "ID_MEMBER")
	private String idUser;

	@Column(name = "USERNAME")
	private String username;

	@Column(name = "PASSWORD")
	private String password;

	/**
	 * @OneToMany(fetch = FetchType.EAGER, cascade=CascadeType.PERSIST)
	 * @JoinColumn(name = "ID_MEMBER")
	 * private Collection<Role> roles = new ArrayList<>();
	 * <p>
	 * public Collection<Role> getRoles() {
	 * return roles;
	 * }
	 * <p>
	 * public Member setRoles(Collection<Role> roles) {
	 * this.roles = roles;
	 * return this;
	 * }
	 */

	public String getIdUser() {
		return idUser;
	}

	public Member setIdUser(String userId) {
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

	public Member setPassword(String password) {
		this.password = password;
		return this;
	}

	@Override
	public String getUsername() {
		return username;
	}

	public Member setUsername(String username) {
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
