package com.br.igorsily.cursomc;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.br.igorsily.cursomc.model.pagamento.Pagamento;
import com.br.igorsily.cursomc.model.pagamento.PagamentoCartao;
import com.br.igorsily.cursomc.model.categoria.Categoria;
import com.br.igorsily.cursomc.model.cidade.Cidade;
import com.br.igorsily.cursomc.model.cliente.Cliente;
import com.br.igorsily.cursomc.model.endereco.Endereco;
import com.br.igorsily.cursomc.model.enums.EstadoPagamento;
import com.br.igorsily.cursomc.model.enums.TipoCliente;
import com.br.igorsily.cursomc.model.estado.Estado;
import com.br.igorsily.cursomc.model.pedido.Pedido;
import com.br.igorsily.cursomc.model.produto.Produto;
import com.br.igorsily.cursomc.repository.categoria.CategoriaRepository;
import com.br.igorsily.cursomc.repository.cidade.CidadeRepository;
import com.br.igorsily.cursomc.repository.cliente.ClienteRepository;
import com.br.igorsily.cursomc.repository.endereco.EnderecoRepository;
import com.br.igorsily.cursomc.repository.estado.EstadoRepository;
import com.br.igorsily.cursomc.repository.pagamento.PagamentoRepository;
import com.br.igorsily.cursomc.repository.pedido.PedidoRepository;
import com.br.igorsily.cursomc.repository.produto.ProdutoRepository;

@SpringBootApplication
public class CursomcApplication implements CommandLineRunner {

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Autowired
	private ProdutoRepository produtoRepository;

	@Autowired
	private CidadeRepository cidadeRepository;

	@Autowired
	private EstadoRepository estadoRepository;
	@Autowired
	private ClienteRepository clienteRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private PedidoRepository pedidoRepository;
	@Autowired
	private PagamentoRepository pagamentoRepository;

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria categoria = new Categoria(null, "Informática");
		Categoria categoria2 = new Categoria(null, "Escritório");
		Categoria categoria3 = new Categoria(null, "Mesa");
		Categoria categoria4 = new Categoria(null, "Banho");
		Categoria categoria5 = new Categoria(null, "Roupa");
		Categoria categoria6 = new Categoria(null, "Cadeira");
		Categoria categoria7 = new Categoria(null, "Mouse");
		Categoria categoria8 = new Categoria(null, "Tenis");

		Produto produto = new Produto(null, "Computador", 2000.00);
		Produto produto2 = new Produto(null, "Impressora", 800.00);
		Produto produto3 = new Produto(null, "Mouse", 80.00);

		categoria.setProdutos(Arrays.asList(produto, produto2, produto3));
		categoria2.setProdutos(Arrays.asList(produto2));

		produto.setCategorias(Arrays.asList(categoria));
		produto2.setCategorias(Arrays.asList(categoria, categoria2));
		produto3.setCategorias(Arrays.asList(categoria));

		Estado estado = new Estado(null, "Minas Gerais");
		Cidade cidade = new Cidade(null, "Uberlândia", estado);

		Cliente cliente = new Cliente(null, "Igor Sily", "igor@example.com", "123456789", TipoCliente.PESSOAFISICA);
		cliente.getTelefone().addAll(Arrays.asList("12345", "6789"));

		Endereco endereco = new Endereco(null, "Rua Flores", "300", "Apto 200", "Jartdim", "29060", cliente, cidade);

		cliente.getEnderecos().addAll(Arrays.asList(endereco));

		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");

		Pedido ped1 = new Pedido(null, sdf.parse("30/09/2017 10:32"), cliente, endereco);
		Pedido ped2 = new Pedido(null, sdf.parse("10/10/2017 19:35"), null, null);

		Pagamento pagto1 = new PagamentoCartao(null, EstadoPagamento.QUITADO, ped1, 6);
		ped1.setPagamento(pagto1);

		cliente.getPedidos().addAll(Arrays.asList(ped1));

		categoriaRepository.saveAll(Arrays.asList(categoria, categoria2, categoria3, categoria4, categoria5, categoria6,
				categoria7, categoria8));
		produtoRepository.saveAll(Arrays.asList(produto, produto2, produto3));
		estadoRepository.save(estado);
		cidadeRepository.save(cidade);
		clienteRepository.save(cliente);
		enderecoRepository.save(endereco);
		pedidoRepository.saveAll(Arrays.asList(ped1));
		pagamentoRepository.saveAll(Arrays.asList(pagto1));
	}

}
