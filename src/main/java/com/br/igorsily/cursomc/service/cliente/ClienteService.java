package com.br.igorsily.cursomc.service.cliente;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.br.igorsily.cursomc.dto.CategoriaDTO;
import com.br.igorsily.cursomc.dto.ClienteDTO;
import com.br.igorsily.cursomc.model.categoria.Categoria;
import com.br.igorsily.cursomc.model.cliente.Cliente;
import com.br.igorsily.cursomc.repository.cliente.ClienteRepository;
import com.br.igorsily.cursomc.service.exception.DataIntegrityException;
import com.br.igorsily.cursomc.service.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;

	public List<Cliente> findAll() {

		return clienteRepository.findAll();

	}

	public Page<Cliente> findPage(Integer page, Integer size, String orderBy, String direction) {

		PageRequest pageRequest = PageRequest.of(page, size, Direction.valueOf(direction), orderBy);
		return clienteRepository.findAll(pageRequest);

	}

	public Cliente findById(Integer id) {

		Optional<Cliente> Cliente = clienteRepository.findById(id);

		return Cliente.orElseThrow(() -> new ObjectNotFoundException(
				"Cliente não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));

	}
	
	public Cliente save(ClienteDTO clienteDto) {

		Cliente cliente = new Cliente(clienteDto.getId(), clienteDto.getNome(), clienteDto.getEmail(), null, null);

		return clienteRepository.save(cliente);

	}
	
	public Cliente update(ClienteDTO clienteDto) {

		Cliente cliente = findById(clienteDto.getId());

		cliente.setNome(clienteDto.getNome());
		cliente.setEmail(clienteDto.getEmail());

		return clienteRepository.save(cliente);
	}
	
	public void delete(Integer id) {

		findById(id);

		try {

			clienteRepository.deleteById(id);

		} catch (DataIntegrityViolationException e) {

			throw new DataIntegrityException("Não é possivel excluir o cliente que tenha produtos ligados a ela");
		}

	}



}
