package com.br.igorsily.cursomc.model.itempedido;

import java.io.Serializable;
import java.text.NumberFormat;
import java.util.Locale;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.br.igorsily.cursomc.model.pedido.Pedido;
import com.br.igorsily.cursomc.model.produto.Produto;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItemPedido implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@JsonIgnore
	private ItemPedidoPk id = new ItemPedidoPk();

	private Double desconto;
	private Integer quantidade;
	private Double preco;

	public ItemPedido() {
	}

	public ItemPedido(Double desconto, Integer quantidade, Double preco, Pedido pedido, Produto produto) {
		super();
		this.desconto = desconto;
		this.quantidade = quantidade;
		this.preco = preco;
		this.id.setPedido(pedido);
		this.id.setProduto(produto);
	}

	public Double getDesconto() {
		return desconto;
	}

	public void setDesconto(Double desconto) {
		this.desconto = desconto;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	@JsonIgnore
	public Pedido getPedido() {
		return id.getPedido();
	}

	public void setPedido(Pedido pedido) {
		id.setPedido(pedido);
	}

	public Produto getProduto() {
		return id.getProduto();
	}

	public void setProduto(Produto produto) {
		id.setProduto(produto);
	}

	public Double getSubTotal() {

		return this.getQuantidade() * (this.getPreco() - this.getDesconto());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemPedido other = (ItemPedido) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	@Override
	public String toString() {

		NumberFormat numberFormat = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));
		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append(this.id.getProduto().getNome());
		stringBuilder.append(", Qte: ");
		stringBuilder.append(this.getQuantidade());
		stringBuilder.append(", Preço unitário: ");
		stringBuilder.append(numberFormat.format(getPreco()));
		stringBuilder.append(", Subtotal: ");
		stringBuilder.append(numberFormat.format(getSubTotal()));
		stringBuilder.append("\n");
		return stringBuilder.toString();
	}

}
