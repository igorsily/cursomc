package com.br.igorsily.cursomc.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import com.br.igorsily.cursomc.service.validation.ClienteSave;

@ClienteSave
public class NewClienteDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "Preenchimeno obrigatório")
	@Size(min = 3, max = 120, message = "O tamanho deve ser entre 3 e 120 caracteres")
	private String nome;
	
	@NotEmpty(message = "Preenchimeno obrigatório")
	@Email(message = "E-mail inválido")
	@Column(unique=true)
	private String email;
	
	@NotEmpty(message = "Preenchimeno obrigatório")
	private String cpfOuCnpj;
	
	private Integer tipoCliente;
	
	@NotEmpty(message = "Preenchimeno obrigatório")
	private String logradouro;
	
	@NotEmpty(message = "Preenchimeno obrigatório")
	private String numero;
	
	private String complemento;
	
	@NotEmpty(message = "Preenchimeno obrigatório")
	private String bairro;
	
	@NotEmpty(message = "Preenchimeno obrigatório")
	private String cep;

	private List<String> telefones = new ArrayList<String>();

	private Integer cidade;

	public NewClienteDTO() {
		// TODO Auto-generated constructor stub
	}

	public NewClienteDTO(String nome, String email, String cpfOuCnpj, Integer tipoCliente, String logradouro,
			String numero, String complemento, String bairro, String cep, List<String> telefones, Integer cidade) {
		super();
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.tipoCliente = tipoCliente;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.telefones = telefones;
		this.cidade = cidade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCpfOuCnpj() {
		return cpfOuCnpj;
	}

	public void setCpfOuCnpj(String cpfOuCnpj) {
		this.cpfOuCnpj = cpfOuCnpj;
	}

	public Integer getTipoCliente() {
		return tipoCliente;
	}

	public void setTipoCliente(Integer tipoCliente) {
		this.tipoCliente = tipoCliente;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public List<String> getTelefones() {
		return telefones;
	}

	public void setTelefones(List<String> telefones) {
		this.telefones = telefones;
	}

	public Integer getCidade() {
		return cidade;
	}

	public void setCidade(Integer cidade) {
		this.cidade = cidade;
	}

}
