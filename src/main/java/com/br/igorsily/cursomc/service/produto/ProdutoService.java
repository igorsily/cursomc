package com.br.igorsily.cursomc.service.produto;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.br.igorsily.cursomc.model.categoria.Categoria;
import com.br.igorsily.cursomc.model.produto.Produto;
import com.br.igorsily.cursomc.repository.categoria.CategoriaRepository;
import com.br.igorsily.cursomc.repository.produto.ProdutoRepository;
import com.br.igorsily.cursomc.service.exception.ObjectNotFoundException;

@Service
public class ProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public Produto find(Integer id) {
		Optional<Produto> produto = produtoRepository.findById(id);
		return produto.orElseThrow(() -> new ObjectNotFoundException(
				"Produto não encontrado! Id: " + id + ", Tipo: " + Produto.class.getName()));
	}
	
	public Page<Produto> search(String nome, Integer page,List<Integer>ids, Integer size, String orderBy, String direction) {
		
		PageRequest pageRequest = PageRequest.of(page, size, Direction.valueOf(direction), orderBy);
		
		List<Categoria> categorias = categoriaRepository.findAllById(ids);
		
		return produtoRepository.findDistincByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
	}

}
