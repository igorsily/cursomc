package com.br.igorsily.cursomc.service.email;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.br.igorsily.cursomc.model.pedido.Pedido;

public interface EmailService {
	
	void sendOrderConfirmationEmail(Pedido pedido);

	void sendEmail(SimpleMailMessage msg);
	
	void sendOrderConfirmationHtmlEmail(Pedido pedido);
	
	void sendHtmlEmail(MimeMessage msg);
	
}
