package com.br.igorsily.cursomc.controller.pedido;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.br.igorsily.cursomc.model.pedido.Pedido;
import com.br.igorsily.cursomc.service.pedido.PedidoService;

@RestController
@RequestMapping(value = "pedidos")
public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id) {

		Pedido obj = pedidoService.findById(id);

		return new ResponseEntity<>(obj, HttpStatus.OK);
	}

}
