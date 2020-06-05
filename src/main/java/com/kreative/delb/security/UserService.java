package com.kreative.delb.security;

import com.kreative.delb.author.model.Author;
import com.kreative.delb.author.repository.AuthorRepository;
import com.kreative.delb.user.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private MemberRepository memberRepository;

	@Autowired
	private AuthorRepository authorRepository;

	public Member createUser(Member user) {
		Member memberCreated = memberRepository.save(user);
		if (user.getAuthorities().contains(new RoleAuthority().setConstantesRole(ConstantesRole.ROLE_AUTHOR))) {
			authorRepository.save(new Author().setIdAuthor(memberCreated.getIdUser()));
		}
		return memberCreated;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Objects.requireNonNull(username);
		return memberRepository.findOneByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));
	}
}
