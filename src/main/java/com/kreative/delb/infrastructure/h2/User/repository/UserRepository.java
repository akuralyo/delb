package com.kreative.delb.infrastructure.h2.User.repository;

import com.kreative.delb.infrastructure.h2.User.model.UserModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends CrudRepository<UserModel, String> {

  Optional<UserModel> findOneByUsername(String username);
}
