package com.br.igorsily.cursomc.repository.cidade;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.igorsily.cursomc.model.cidade.Cidade;

public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

}
