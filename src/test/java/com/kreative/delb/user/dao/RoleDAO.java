package com.kreative.delb.user.dao;

import com.kreative.delb.security.RoleRepository;
import com.kreative.delb.user.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RoleDAO {
	@Autowired
	private RoleRepository roleRepository;

	public List<Role> findAll() {
		List<Role> roleList = new ArrayList<>();
		roleRepository.findAll().forEach(roleList::add);
		return roleList;
	}
}
