package com.kreative.delb.security;

import com.kreative.delb.user.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, String> {

	Optional<Role> findOneByRoleName(String roleName);
}
