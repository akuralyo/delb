package com.kreative.delb.user.dao;

import com.kreative.delb.security.MemberRepository;
import com.kreative.delb.user.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Component
public class MemberDAO {

	@Autowired
	private MemberRepository memberRepository;

	public List<Member> findAll() {
		List<Member> userList = new ArrayList<>();
		memberRepository.findAll().forEach(userList::add);
		return userList;
	}

	public Member findAnyone() {
		List<Member> userList = findAll();
		return userList.get(new Random().nextInt(userList.size()));
	}
}
