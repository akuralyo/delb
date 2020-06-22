package com.kreative.delb.user.dao;

import com.kreative.delb.security.UserRepository;
import com.kreative.delb.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class MemberDAO {

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
