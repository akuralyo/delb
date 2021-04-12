package com.kreative.delb.infrastructure.h2.User.service;

import com.kreative.delb.domain.service.user.model.User;
import com.kreative.delb.domain.spi.UserSpi;
import com.kreative.delb.infrastructure.h2.User.model.UserModel;

import java.util.Optional;

public class UserAdapter implements UserSpi {
  @Override
  public UserModel createUserModel(User user) {
    return null;
  }

  @Override
  public Optional<UserModel> findOneByUsername(String username) {
    return Optional.empty();
  }
}
