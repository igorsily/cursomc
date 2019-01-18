package com.br.igorsily.cursomc.controller.categoria;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.br.igorsily.cursomc.dto.CategoriaDTO;
import com.br.igorsily.cursomc.model.categoria.Categoria;
import com.br.igorsily.cursomc.service.categoria.CategoriaService;

@RestController
@RequestMapping(value = "categorias")
public class CategoriaController {

	@Autowired
	private CategoriaService categoriaService;

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoriaDTO>> findAll() {

		List<Categoria> categorias = categoriaService.findAll();

		List<CategoriaDTO> categoriasDto = categorias.stream().map(categoria -> new CategoriaDTO(categoria))
				.collect(Collectors.toList());

		return new ResponseEntity<List<CategoriaDTO>>(categoriasDto, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}")
	public ResponseEntity<Categoria> findById(@PathVariable Integer id) {

		Categoria categoria = categoriaService.findById(id);

		return new ResponseEntity<Categoria>(categoria, HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> save(@Valid @RequestBody CategoriaDTO categoriaDto) {

		Categoria categoria = categoriaService.save(categoriaDto);

		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId())
				.toUri();

		HttpHeaders headers = new HttpHeaders();

		headers.setLocation(location);

		return new ResponseEntity<Void>(headers, HttpStatus.CREATED);

	}

	@RequestMapping(method = RequestMethod.PUT, value = "/{id}")
	public ResponseEntity<Void> edit(@PathVariable Integer id, @Valid @RequestBody CategoriaDTO categoriaDto) {

		categoriaDto.setId(id);

		categoriaService.update(categoriaDto);

		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Integer id) {

		categoriaService.delete(id);

		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@RequestMapping(method = RequestMethod.GET, value = "/page")
	public ResponseEntity<Page<CategoriaDTO>> findPage(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "size", defaultValue = "10") Integer size,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction) {

		Page<Categoria> categorias = categoriaService.findPage(page, size, orderBy, direction);
		Page<CategoriaDTO> categoriaDto = categorias.map(categoria -> new CategoriaDTO(categoria));
		return new ResponseEntity<Page<CategoriaDTO>>(categoriaDto, HttpStatus.OK);
	}

}
