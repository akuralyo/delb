package com.kreative.delb.infrastructure.h2.author.config;

import com.kreative.delb.infrastructure.h2.author.repository.AuthorRepository;
import com.kreative.delb.infrastructure.h2.author.adapter.AuthorAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class H2AuthorConfig {

  @Bean
  AuthorAdapter authorAdapter(AuthorRepository authorRepository) {

    return new AuthorAdapter(authorRepository);
  }
}
