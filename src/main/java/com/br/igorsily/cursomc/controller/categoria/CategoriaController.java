package com.br.igorsily.cursomc.controller.categoria;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.br.igorsily.cursomc.model.categoria.Categoria;
import com.br.igorsily.cursomc.service.categoria.CategoriaService;

@RestController
@RequestMapping(value = "categorias")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id) {

		Categoria obj = categoriaService.findById(id);

		return new ResponseEntity<>(obj, HttpStatus.OK);
	}

}
