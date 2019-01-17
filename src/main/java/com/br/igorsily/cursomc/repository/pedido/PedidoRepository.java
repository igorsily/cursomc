package com.br.igorsily.cursomc.repository.pedido;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.br.igorsily.cursomc.model.pedido.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Integer> {

}
