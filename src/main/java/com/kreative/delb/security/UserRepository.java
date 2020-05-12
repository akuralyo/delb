package com.kreative.delb.security;

import com.kreative.delb.user.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, String> {

	Optional<User> findOneByUsername(String username);
}
