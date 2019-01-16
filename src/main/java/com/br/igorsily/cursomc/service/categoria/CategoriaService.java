package com.br.igorsily.cursomc.service.categoria;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.igorsily.cursomc.model.categoria.Categoria;
import com.br.igorsily.cursomc.repository.categoria.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Categoria findById(Integer id) {

		Optional<Categoria> obj = categoriaRepository.findById(id);
		return obj.orElse(null);
	}

}
