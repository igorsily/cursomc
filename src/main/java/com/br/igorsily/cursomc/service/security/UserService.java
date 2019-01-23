package com.br.igorsily.cursomc.service.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.br.igorsily.cursomc.security.UserSecurity;

@Service
public class UserService {

	public static UserSecurity authenticated() {

		try {
			return (UserSecurity) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}

	}

}
