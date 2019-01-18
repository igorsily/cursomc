package com.br.igorsily.cursomc.service.categoria;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.br.igorsily.cursomc.dto.CategoriaDTO;
import com.br.igorsily.cursomc.model.categoria.Categoria;
import com.br.igorsily.cursomc.repository.categoria.CategoriaRepository;
import com.br.igorsily.cursomc.service.exception.DataIntegrityException;
import com.br.igorsily.cursomc.service.exception.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	public List<Categoria> findAll() {

		return categoriaRepository.findAll();

	}

	public Page<Categoria> findPage(Integer page, Integer size, String orderBy, String direction) {

		PageRequest pageRequest = PageRequest.of(page, size, Direction.valueOf(direction), orderBy);
		return categoriaRepository.findAll(pageRequest);

	}

	public Categoria findById(Integer id) {

		Optional<Categoria> categoria = categoriaRepository.findById(id);
		return categoria.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	public Categoria save(CategoriaDTO categoriaDto) {

		Categoria categoria = new Categoria(categoriaDto.getId(), categoriaDto.getNome());

		return categoriaRepository.save(categoria);

	}

	public Categoria update(CategoriaDTO categoriaDto) {

		Categoria categoria = findById(categoriaDto.getId());

		categoria.setNome(categoriaDto.getNome());

		return categoriaRepository.save(categoria);
	}

	public void delete(Integer id) {

		findById(id);

		try {

			categoriaRepository.deleteById(id);

		} catch (DataIntegrityViolationException e) {

			throw new DataIntegrityException("Não é possivel excluir uma categoria que tenha produtos ligados a ela");
		}

	}

}
