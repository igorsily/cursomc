package com.br.igorsily.cursomc.controller.categoria;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.br.igorsily.cursomc.model.categoria.Categoria;
import com.br.igorsily.cursomc.service.categoria.CategoriaService;

@RestController
@RequestMapping(value = "categorias")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<Categoria> findById(@PathVariable Integer id) {

		Categoria categoria = categoriaService.findById(id);

		return new ResponseEntity<Categoria>(categoria,HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> save(@RequestBody Categoria categoria) {

		categoria = categoriaService.save(categoria);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId())
				.toUri();
		
		HttpHeaders headers = new HttpHeaders();
		
		headers.setLocation(location);
		
		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);

	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<Void> edit(@PathVariable Integer id, @RequestBody Categoria categoria) {
		
		categoria.setId(id);
		
		categoria = categoriaService.update(categoria);
		
	
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {
		
		categoriaService.delete(id);
	
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}
