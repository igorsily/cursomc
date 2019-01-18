package com.br.igorsily.cursomc.service.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.br.igorsily.cursomc.controller.exception.FieldMessage;
import com.br.igorsily.cursomc.dto.ClienteDTO;
import com.br.igorsily.cursomc.dto.NewClienteDTO;
import com.br.igorsily.cursomc.model.cliente.Cliente;
import com.br.igorsily.cursomc.model.enums.TipoCliente;
import com.br.igorsily.cursomc.repository.cliente.ClienteRepository;
import com.br.igorsily.cursomc.service.validation.utils.BR;

public class ClienteUpdateValidation implements ConstraintValidator<ClienteUpdate, ClienteDTO> {

	@Autowired
	private ClienteRepository clienteRepository;

	@Autowired
	private HttpServletRequest request;

	@Override
	public void initialize(ClienteUpdate ann) {
	}

	@Override
	public boolean isValid(ClienteDTO clienteDto, ConstraintValidatorContext context) {
		List<FieldMessage> fieldMessages = new ArrayList<>();

		@SuppressWarnings("unchecked")
		Map<String, String> map = (Map<String, String>) request
				.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);

		Integer id = Integer.parseInt(map.get("id"));

		Cliente cliente = clienteRepository.findByEmail(clienteDto.getEmail());

		if (cliente != null && !cliente.getId().equals(id)) {
			fieldMessages.add(new FieldMessage("email", "Email já cadastrado"));
		}

		for (FieldMessage e : fieldMessages) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getField())
					.addConstraintViolation();
		}
		return fieldMessages.isEmpty();
	}

}
