package com.br.igorsily.cursomc.repository.produto;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.igorsily.cursomc.model.produto.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

}
