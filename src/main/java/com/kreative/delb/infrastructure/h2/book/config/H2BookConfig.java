package com.kreative.delb.infrastructure.h2.book.config;

import com.kreative.delb.infrastructure.h2.book.repository.BookRepository;
import com.kreative.delb.infrastructure.h2.book.service.BookAdapter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class H2BookConfig {

  @Bean
  BookAdapter bookAdapter(BookRepository bookRepository) {
    return new BookAdapter(bookRepository);
  }
}
