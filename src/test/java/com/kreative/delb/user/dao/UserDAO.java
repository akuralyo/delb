package com.kreative.delb.user.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.kreative.delb.infrastructure.h2.User.model.UserModel;
import com.kreative.delb.infrastructure.h2.User.repository.UserRepository;

@Component
public class UserDAO {

  @Autowired private UserRepository userRepository;

  public List<UserModel> findAll() {
    final List<UserModel> userList = new ArrayList<>();
    userRepository.findAll().forEach(userList::add);
    return userList;
  }

  public UserModel findAnyone() {
    final List<UserModel> userList = findAll();
    return userList.get(new Random().nextInt(userList.size()));
  }
}
