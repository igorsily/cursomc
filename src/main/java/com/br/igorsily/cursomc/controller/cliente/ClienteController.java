package com.br.igorsily.cursomc.controller.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.br.igorsily.cursomc.model.cliente.Cliente;
import com.br.igorsily.cursomc.service.cliente.ClienteService;

@RestController
@RequestMapping(value = "clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<Cliente> findById(@PathVariable Integer id) {

		Cliente cliente = clienteService.findById(id);

		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}


}
