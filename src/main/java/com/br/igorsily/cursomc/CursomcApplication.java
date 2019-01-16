package com.br.igorsily.cursomc;

import java.util.Arrays;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.br.igorsily.cursomc.model.categoria.Categoria;
import com.br.igorsily.cursomc.model.cidade.Cidade;
import com.br.igorsily.cursomc.model.cliente.Cliente;
import com.br.igorsily.cursomc.model.endereco.Endereco;
import com.br.igorsily.cursomc.model.enums.TipoCliente;
import com.br.igorsily.cursomc.model.estado.Estado;
import com.br.igorsily.cursomc.model.produto.Produto;
import com.br.igorsily.cursomc.repository.categoria.CategoriaRepository;
import com.br.igorsily.cursomc.repository.cidade.CidadeRepository;
import com.br.igorsily.cursomc.repository.cliente.ClienteRepository;
import com.br.igorsily.cursomc.repository.endereco.EnderecoRepository;
import com.br.igorsily.cursomc.repository.estado.EstadoRepository;
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

	public static void main(String[] args) {
		SpringApplication.run(CursomcApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Categoria categoria = new Categoria(null, "Informática");
		Categoria categoria2 = new Categoria(null, "Escritório");
 
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

		categoriaRepository.saveAll(Arrays.asList(categoria, categoria2));
		produtoRepository.saveAll(Arrays.asList(produto, produto2, produto3));
		estadoRepository.save(estado);
		cidadeRepository.save(cidade);
		clienteRepository.save(cliente);
		enderecoRepository.save(endereco);
	}

}
