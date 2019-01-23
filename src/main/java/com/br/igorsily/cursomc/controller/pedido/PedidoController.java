package com.br.igorsily.cursomc.controller.pedido;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.br.igorsily.cursomc.model.pedido.Pedido;
import com.br.igorsily.cursomc.service.pedido.PedidoService;

@RestController
@RequestMapping(value = "pedidos")
public class PedidoController {

	@Autowired
	private PedidoService pedidoService;

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<Pedido> findById(@PathVariable Integer id) {

		Pedido pedido = pedidoService.findById(id);

		return new ResponseEntity<Pedido>(pedido, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> save(@RequestBody Pedido obj) {

		Pedido pedido = pedidoService.save(obj);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(pedido.getId())
				.toUri();

		HttpHeaders headers = new HttpHeaders();

		headers.setLocation(location);

		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);

	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<Pedido>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPerPage", defaultValue = "24") Integer linesPerPage,
			@RequestParam(value = "orderBy", defaultValue = "instante") String orderBy,
			@RequestParam(value = "direction", defaultValue = "DESC") String direction) {

		Page<Pedido> pedidos = pedidoService.findPage(page, linesPerPage, orderBy, direction);
		return new ResponseEntity<Page<Pedido>>(pedidos, HttpStatus.OK);
	}

}
