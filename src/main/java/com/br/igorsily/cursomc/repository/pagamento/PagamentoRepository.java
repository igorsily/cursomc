package com.br.igorsily.cursomc.repository.pagamento;

import org.springframework.data.jpa.repository.JpaRepository;

import com.br.igorsily.cursomc.model.Pagamento.Pagamento;

public interface PagamentoRepository extends JpaRepository<Pagamento, Integer> {

}
