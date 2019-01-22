package com.br.igorsily.cursomc.service.email;

import org.springframework.mail.SimpleMailMessage;

import com.br.igorsily.cursomc.model.pedido.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido pedido);

	void sendEmail(SimpleMailMessage msg);
	
}
