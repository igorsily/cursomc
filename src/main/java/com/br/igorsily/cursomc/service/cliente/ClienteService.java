package com.br.igorsily.cursomc.service.cliente;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.br.igorsily.cursomc.dto.ClienteDTO;
import com.br.igorsily.cursomc.dto.NewClienteDTO;
import com.br.igorsily.cursomc.model.cidade.Cidade;
import com.br.igorsily.cursomc.model.cliente.Cliente;
import com.br.igorsily.cursomc.model.endereco.Endereco;
import com.br.igorsily.cursomc.model.enums.TipoCliente;
import com.br.igorsily.cursomc.repository.cliente.ClienteRepository;
import com.br.igorsily.cursomc.repository.endereco.EnderecoRepository;
import com.br.igorsily.cursomc.service.exception.DataIntegrityException;
import com.br.igorsily.cursomc.service.exception.ObjectNotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;

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

	@Transactional
	public Cliente save(NewClienteDTO newClienteDto) {

		Cliente cliente = new Cliente(null, newClienteDto.getNome(), newClienteDto.getEmail(),
				newClienteDto.getCpfOuCnpj(), TipoCliente.toEnum(newClienteDto.getTipoCliente()));

		Cidade cidade = new Cidade(newClienteDto.getCidade(), null, null);

		Endereco endereco = new Endereco(null, newClienteDto.getLogradouro(), newClienteDto.getNumero(),
				newClienteDto.getComplemento(), newClienteDto.getBairro(), newClienteDto.getCep(), cliente, cidade);

		cliente.getEnderecos().add(endereco);

		for (String telefone : newClienteDto.getTelefones()) {

			cliente.getTelefone().add(telefone);

		}
		enderecoRepository.save(endereco);
		
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
