package com.kreative.delb.infrastructure.h2.User.config;

import com.kreative.delb.infrastructure.h2.User.service.UserAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class H2UserConfig {

  @Bean
  UserAdapter userAdapter() {
    return new UserAdapter();
  }
}
