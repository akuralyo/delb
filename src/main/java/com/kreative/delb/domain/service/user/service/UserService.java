package com.kreative.delb.domain.service.user.service;

import com.kreative.delb.application.security.ConstantesRole;
import com.kreative.delb.application.security.RoleAuthority;
import com.kreative.delb.domain.service.user.mapper.UserMapper;
import com.kreative.delb.domain.service.user.model.User;
import com.kreative.delb.domain.spi.AuthorSpi;
import com.kreative.delb.domain.spi.UserSpi;
import com.kreative.delb.infrastructure.h2.User.model.UserModel;
import com.kreative.delb.infrastructure.h2.author.model.AuthorModel;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class UserService implements UserDetailsService {

  private final AuthorSpi authorSpi;

  private final UserMapper userMapper;

  private final UserSpi userSpi;

  public UserService(
      final UserSpi userSpi, final AuthorSpi authorSpi, final UserMapper userMapper) {
    this.userSpi = userSpi;
    this.authorSpi = authorSpi;
    this.userMapper = userMapper;
  }

  public User createUser(final User user) {
    final UserModel userModel = userSpi.createUserModel(user);
    if (userModel
        .getAuthorities()
        .contains(new RoleAuthority().setConstantesRole(ConstantesRole.ROLE_AUTHOR))) {

      final AuthorModel authorModel = new AuthorModel();
      authorModel.setIdAuthor(userModel.getId());
      authorSpi.createAuthor(authorModel);
    }
    return userMapper.mapToDto(userModel);
  }

  @Override
  public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
    Objects.requireNonNull(username);
    return userSpi
        .findOneByUsername(username)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));
  }
}
