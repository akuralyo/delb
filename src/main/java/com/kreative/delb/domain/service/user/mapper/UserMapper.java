package com.kreative.delb.domain.service.user.mapper;

import org.springframework.stereotype.Component;

import com.kreative.delb.application.common.utils.Transformer;
import com.kreative.delb.domain.service.user.model.User;
import com.kreative.delb.infrastructure.h2.User.model.UserModel;

@Component
public class UserMapper implements Transformer<UserModel, User> {
  @Override
  public User mapToDto(UserModel source) {
    return null;
  }

  @Override
  public UserModel mapToModel(User source) {
    return null;
  }
}
