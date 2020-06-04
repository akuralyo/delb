package com.kreative.delb.security;

import com.kreative.delb.user.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<User, String> {

	Optional<User> findOneByUsername(String username);
}
