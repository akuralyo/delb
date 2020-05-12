package com.kreative.delb.security;

import com.kreative.delb.author.model.Author;
import com.kreative.delb.author.repository.AuthorRepository;
import com.kreative.delb.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private AuthorRepository authorRepository;

	public User createUser(User user) {
		User userCreated = userRepository.save(user);
		if (user.getAuthorities().contains(new RoleAuthority().setRole(Role.AUTHOR))) {
			authorRepository.save(new Author().setUserId(userCreated.getUserId()));
		}
		return userCreated;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Objects.requireNonNull(username);
		return userRepository.findOneByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}
}
