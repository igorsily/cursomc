package com.br.igorsily.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.br.igorsily.cursomc.service.database.DatabaseService;

@Configuration
@Profile("test")
public class ProfileTestConfig {
	
	@Autowired
	private DatabaseService databaseService;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		
		databaseService.instantiateTestDatabase();
		return true;
	}

}
