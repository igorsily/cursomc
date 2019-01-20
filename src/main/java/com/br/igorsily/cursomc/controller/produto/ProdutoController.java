package com.br.igorsily.cursomc.controller.produto;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.br.igorsily.cursomc.dto.ProdutoDTO;
import com.br.igorsily.cursomc.model.produto.Produto;
import com.br.igorsily.cursomc.service.produto.ProdutoService;

@RestController
@RequestMapping(value = "produtos")
public class ProdutoController {

	@Autowired
	private ProdutoService produtoService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Page<ProdutoDTO>> findPage(@RequestParam(value = "nome", defaultValue = "") String nome,
			@RequestParam(value = "categorias", defaultValue = "") String categorias,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {

		List<Integer> ids = new ArrayList<Integer>();

		ids = Arrays.asList(categorias.split(",")).stream().map(id -> Integer.parseInt(id))
				.collect(Collectors.toList());
		try {
			nome = URLDecoder.decode(nome, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		Page<Produto> produtos = produtoService.search(nome, page, ids, size, orderBy, direction);
		Page<ProdutoDTO> produtoDto = produtos.map(produto -> new ProdutoDTO(produto));
		return new ResponseEntity<Page<ProdutoDTO>>(produtoDto, HttpStatus.OK);
		
	}

}
