package com.kreative.delb.domain.spi;

import com.kreative.delb.domain.service.user.model.User;
import com.kreative.delb.infrastructure.h2.User.model.UserModel;

import java.util.Optional;

public interface UserSpi {

  UserModel createUserModel(User user);

  Optional<UserModel> findOneByUsername(String username);
}
