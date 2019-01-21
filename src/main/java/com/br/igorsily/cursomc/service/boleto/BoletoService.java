package com.br.igorsily.cursomc.service.boleto;

import java.util.Calendar;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.br.igorsily.cursomc.model.pagamento.PagamentoBoleto;

@Service
public class BoletoService {

	public void setDataVencimentoBoleto(PagamentoBoleto pagamento, Date dataPedido) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(dataPedido);
		cal.add(Calendar.DAY_OF_MONTH, 7);
		pagamento.setDataVencimento(cal.getTime());
	}
}
