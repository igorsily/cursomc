package com.br.igorsily.cursomc.service.pedido;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.br.igorsily.cursomc.model.cliente.Cliente;
import com.br.igorsily.cursomc.model.enums.EstadoPagamento;
import com.br.igorsily.cursomc.model.enums.Perfil;
import com.br.igorsily.cursomc.model.itempedido.ItemPedido;
import com.br.igorsily.cursomc.model.pagamento.PagamentoBoleto;
import com.br.igorsily.cursomc.model.pedido.Pedido;
import com.br.igorsily.cursomc.repository.itempedido.ItemPedidoRepository;
import com.br.igorsily.cursomc.repository.pagamento.PagamentoRepository;
import com.br.igorsily.cursomc.repository.pedido.PedidoRepository;
import com.br.igorsily.cursomc.security.UserSecurity;
import com.br.igorsily.cursomc.service.boleto.BoletoService;
import com.br.igorsily.cursomc.service.cliente.ClienteService;
import com.br.igorsily.cursomc.service.email.EmailService;
import com.br.igorsily.cursomc.service.exception.AuthorizationException;
import com.br.igorsily.cursomc.service.exception.ObjectNotFoundException;
import com.br.igorsily.cursomc.service.produto.ProdutoService;
import com.br.igorsily.cursomc.service.security.UserService;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository pedidoRepository;

	@Autowired
	private PagamentoRepository pagamentoRepository;

	@Autowired
	private ItemPedidoRepository itemPedidoRepository;

	@Autowired
	private ProdutoService produtoService;

	@Autowired
	private BoletoService boletoService;

	@Autowired
	private ClienteService clienteService;

	@Autowired
	EmailService emailService;

	public Pedido findById(Integer id) {

		Optional<Pedido> pedido = pedidoRepository.findById(id);

		return pedido.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Pedido.class.getName()));

	}

	public Pedido save(Pedido pedido) {

		pedido.setInstante(new Date());
		pedido.setCliente(clienteService.findById(pedido.getCliente().getId()));
		pedido.getPagamento().setEstadoPagamento(EstadoPagamento.PENDENTE);
		pedido.getPagamento().setPedido(pedido);

		if (pedido.getPagamento() instanceof PagamentoBoleto) {
			PagamentoBoleto pagamento = (PagamentoBoleto) pedido.getPagamento();
			boletoService.setDataVencimentoBoleto(pagamento, pedido.getInstante());
		}

		pedido = pedidoRepository.save(pedido);

		pagamentoRepository.save(pedido.getPagamento());

		for (ItemPedido itemPedido : pedido.getItems()) {

			itemPedido.setDesconto(0.0);
			itemPedido.setProduto(produtoService.findById(itemPedido.getProduto().getId()));
			itemPedido.setPreco(itemPedido.getProduto().getPreco());
			itemPedido.setPedido(pedido);

		}

		itemPedidoRepository.saveAll(pedido.getItems());
		emailService.sendOrderConfirmationHtmlEmail(pedido);
		return pedido;
	}

	public Page<Pedido> findPage(Integer page, Integer linesPerPage, String orderBy, String direction) {
		UserSecurity user = UserService.authenticated();
		if (user == null) {
			throw new AuthorizationException("Acesso negado");
		}
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		Cliente cliente = clienteService.findById(user.getId());
		return pedidoRepository.findByCliente(cliente, pageRequest);
	}
}
