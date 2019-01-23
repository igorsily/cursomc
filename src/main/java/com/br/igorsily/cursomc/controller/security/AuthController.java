package com.br.igorsily.cursomc.controller.security;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.br.igorsily.cursomc.security.JWTUtil;
import com.br.igorsily.cursomc.security.UserSecurity;
import com.br.igorsily.cursomc.service.security.UserService;

@RestController
@RequestMapping(value = "auth")
public class AuthController {

	@Autowired
	private JWTUtil jwtUtil;

	@RequestMapping(value = "/refresh_token", method = RequestMethod.POST)
	public ResponseEntity<Void> refreshToken(HttpServletResponse response) {
		UserSecurity user = UserService.authenticated();
		String token = jwtUtil.generateToken(user.getUsername());
		response.addHeader("Authorization", "Bearer " + token);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
}
