package com.kreative.delb.security;

import com.kreative.delb.user.Member;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MemberRepository extends CrudRepository<Member, String> {

	Optional<Member> findOneByUsername(String username);
}
