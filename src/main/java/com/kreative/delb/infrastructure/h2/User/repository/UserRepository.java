package com.kreative.delb.infrastructure.h2.User.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kreative.delb.infrastructure.h2.User.model.UserModel;

@Repository
public interface UserRepository extends CrudRepository<UserModel, String> {

  Optional<UserModel> findOneByUsername(String username);
}
