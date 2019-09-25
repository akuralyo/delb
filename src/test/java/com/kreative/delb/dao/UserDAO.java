package com.kreative.delb.dao;

import com.kreative.delb.model.User;
import com.kreative.delb.security.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserDAO {

	@Autowired
	private UserRepository userRepository;

	public void deleteAll() {
		userRepository.deleteAll();
	}

	public void createAdmin() {
		User user = new User().setUsername("ADMIN")
				.setPassword("ADMIN");
		userRepository.save(user);
	}
}
