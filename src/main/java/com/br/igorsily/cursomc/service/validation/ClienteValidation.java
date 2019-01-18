package com.br.igorsily.cursomc.service.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.br.igorsily.cursomc.controller.exception.FieldMessage;
import com.br.igorsily.cursomc.dto.NewClienteDTO;
import com.br.igorsily.cursomc.model.enums.TipoCliente;
import com.br.igorsily.cursomc.service.validation.utils.BR;

public class ClienteValidation implements ConstraintValidator<Cliente, NewClienteDTO> {

	@Override
	public void initialize(Cliente ann) {
	}

	@Override
	public boolean isValid(NewClienteDTO newClienteDto, ConstraintValidatorContext context) {
		List<FieldMessage> fieldMessages = new ArrayList<>();

		if (newClienteDto.getTipoCliente().equals(TipoCliente.PESSOAFISICA.getCod())
				&& !BR.isValidCPF(newClienteDto.getCpfOuCnpj())) {

			fieldMessages.add(new FieldMessage("cpfOuCnpj", "CPF Inválido"));

		}

		if (newClienteDto.getTipoCliente().equals(TipoCliente.PESSOAJURIDICA.getCod())
				&& !BR.isValidCNPJ(newClienteDto.getCpfOuCnpj())) {

			fieldMessages.add(new FieldMessage("cpfOuCnpj", "CNPJ Inválido"));

		}

		for (FieldMessage e : fieldMessages) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMessage()).addPropertyNode(e.getField())
					.addConstraintViolation();
		}
		return fieldMessages.isEmpty();
	}

}
