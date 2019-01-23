package com.br.igorsily.cursomc.security;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.web.server.ServerHttpSecurity.OAuth2ResourceServerSpec.JwtSpec;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTUtil {

	@Value("${jwt.secret}")
	private String secret;

	@Value("${jwt.expiration}")
	private Long timeExpiration;

	public String generateToken(String username) {

		return Jwts.builder().setSubject(username).setExpiration(new Date(System.currentTimeMillis() + timeExpiration))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes()).compact();

	}
}
