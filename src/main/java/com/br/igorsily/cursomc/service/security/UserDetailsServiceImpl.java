package com.br.igorsily.cursomc.service.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.br.igorsily.cursomc.model.cliente.Cliente;
import com.br.igorsily.cursomc.repository.cliente.ClienteRepository;
import com.br.igorsily.cursomc.security.UserSecurity;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		Cliente cliente = clienteRepository.findByEmail(email);
		
		if(cliente == null) throw new UsernameNotFoundException(email);
		
		return new UserSecurity(cliente.getId(), cliente.getEmail(), cliente.getSenha(), cliente.getPerfil());
	}

}
