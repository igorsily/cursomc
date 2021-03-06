package com.br.igorsily.cursomc.controller.cliente;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.br.igorsily.cursomc.dto.CategoriaDTO;
import com.br.igorsily.cursomc.dto.ClienteDTO;
import com.br.igorsily.cursomc.dto.NewClienteDTO;
import com.br.igorsily.cursomc.model.cliente.Cliente;
import com.br.igorsily.cursomc.service.cliente.ClienteService;

@RestController
@RequestMapping(value = "clientes")
public class ClienteController {
	
	@Autowired
	private ClienteService clienteService;
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ClienteDTO>> findAll() {

		List<Cliente> clientes = clienteService.findAll();

		List<ClienteDTO> clientesDto = clientes.stream().map(cliente -> new ClienteDTO(cliente))
				.collect(Collectors.toList());

		return new ResponseEntity<List<ClienteDTO>>(clientesDto, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<Cliente> findById(@PathVariable Integer id) {

		Cliente cliente = clienteService.findById(id);

		return new ResponseEntity<Cliente>(cliente, HttpStatus.OK);
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> save(@Valid @RequestBody NewClienteDTO newClienteDto) {

		Cliente cliente = clienteService.save(newClienteDto);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId())
				.toUri();

		HttpHeaders headers = new HttpHeaders();

		headers.setLocation(location);

		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);

	}

	
	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<Void> edit(@PathVariable Integer id, @Valid @RequestBody ClienteDTO clienteDto) {

		clienteDto.setId(id);

		clienteService.update(clienteDto);

		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {

		clienteService.delete(id);

		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(method = RequestMethod.GET, value = "/page")
	public ResponseEntity<Page<ClienteDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {

		Page<Cliente> clientes = clienteService.findPage(page, size, orderBy, direction);
		Page<ClienteDTO> clienteDto = clientes.map(cliente -> new ClienteDTO(cliente));
		return new ResponseEntity<Page<ClienteDTO>>(clienteDto, HttpStatus.OK);
	}

}
