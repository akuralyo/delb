package com.kreative.delb.dao;

import com.kreative.delb.model.User;
import com.kreative.delb.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class UserDAO {

	@Autowired
	private UserRepository userRepository;

	public List<User> findAll() {
		List<User> userList = new ArrayList<>();
		userRepository.findAll().forEach(userList::add);
		return userList;
	}

	public User findAnyone() {
		List<User> userList = findAll();
		return userList.get(new Random().nextInt(userList.size()));
	}
}
