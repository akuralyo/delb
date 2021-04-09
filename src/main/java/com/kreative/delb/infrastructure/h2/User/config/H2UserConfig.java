package com.kreative.delb.infrastructure.h2.User.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.kreative.delb.infrastructure.h2.User.service.UserAdapter;

@Configuration
public class H2UserConfig {

  @Bean
  UserAdapter userAdapter() {
    return new UserAdapter();
  }
}
