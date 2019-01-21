package com.br.igorsily.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.br.igorsily.cursomc.service.database.DatabaseService;

@Configuration
@Profile("dev")
public class ProfileDevConfig {

	@Autowired
	private DatabaseService databaseService;

	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;

	@Bean
	public boolean instantiateDatabase() throws ParseException {

		if (!strategy.equals("create")) {

			return false;
		}

		databaseService.instantiateTestDatabase();
		return true;
	}

}
