package com.br.igorsily.cursomc.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.br.igorsily.cursomc.model.Categoria;

@RestController
@RequestMapping(value = "categorias")
public class CategoriaController {
	
	@RequestMapping(method = RequestMethod.GET)
	public List<Categoria> listar() {
		
		Categoria c1 = new Categoria(1, "Informática");
		Categoria c2 = new Categoria(2, "Escritório");
		
		List<Categoria> lista = new ArrayList<Categoria>();
		
		lista.add(c1);
		lista.add(c2);
		
		return lista;
	}

}
