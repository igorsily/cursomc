package com.br.igorsily.cursomc.repository.estado;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.igorsily.cursomc.model.estado.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Integer> {

}
