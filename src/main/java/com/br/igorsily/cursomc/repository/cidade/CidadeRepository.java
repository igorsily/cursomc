package com.br.igorsily.cursomc.repository.cidade;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.igorsily.cursomc.model.cidade.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer> {

}
