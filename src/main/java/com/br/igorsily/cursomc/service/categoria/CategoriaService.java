package com.br.igorsily.cursomc.service.categoria;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.br.igorsily.cursomc.model.categoria.Categoria;
import com.br.igorsily.cursomc.repository.categoria.CategoriaRepository;
import com.br.igorsily.cursomc.service.exception.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public Categoria findById(Integer id) {

		Optional<Categoria> categoria = categoriaRepository.findById(id);
		return categoria.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	public Categoria save(Categoria categoria) {

		return categoriaRepository.save(categoria);

	}

	public Categoria update(Categoria categoria) {

		findById(categoria.getId());

		return categoriaRepository.save(categoria);
	}

}
