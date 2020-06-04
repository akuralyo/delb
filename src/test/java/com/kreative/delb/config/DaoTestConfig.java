package com.kreative.delb.config;

import com.kreative.delb.author.dao.AuthorDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaoTestConfig {

	@Bean
	public AuthorDAO authorDAO() {
		return new AuthorDAO();
	}

	;
}
