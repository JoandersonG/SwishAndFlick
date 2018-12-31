package com.example.joanderson.bruxosbruxas.model.produto;

import com.example.joanderson.bruxosbruxas.model.Dinheiro;

import java.io.Serializable;

public abstract class Produto implements Serializable {
	//todo: classe abstrata
	private String nome;
	private String descricao;
	private Dinheiro preco;
	private int estoque;
	private int imagem;
	
	public Produto(String nome, String descricao, Dinheiro preco, int estoque, int imagem) {

		if (nome == null || descricao == null || preco == null) {
			throw new IllegalArgumentException();
		}

		this.nome = nome;
		this.descricao = descricao;
		this.preco = preco;
		this.estoque = estoque;
		this.imagem = imagem;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Dinheiro getPreco() {
		return preco;
	}

	public void setPreco(Dinheiro preco) {
		this.preco = preco;
	}

	public int getEstoque() {
		return estoque;
	}

	public void setEstoque(int estoque) {
		this.estoque = estoque;
	}

	public int getImagem() {
		return imagem;
	}

	public void setImagem(int imagem) {
		this.imagem = imagem;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Produto produto = (Produto) o;
		return estoque == produto.estoque
				&& imagem == produto.imagem
				&& preco.equals(produto.preco)
				&& descricao.equals(produto.descricao)
				&& nome.equals(produto.nome);
	}

}
