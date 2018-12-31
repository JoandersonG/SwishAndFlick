package com.example.joanderson.bruxosbruxas.model.produto;

import com.example.joanderson.bruxosbruxas.model.Dinheiro;

public class Vestimenta extends Produto {
	//todo: herança
	private String categoria;
	private char tamanho;

	public Vestimenta(String nome, String descricao, Dinheiro preco, int estoque, int imagem, String categoria, char tamanho) {
		super(nome, descricao, preco, estoque, imagem);

		if (categoria == null) {
			throw new IllegalArgumentException();
		}
		this.categoria = categoria;
		this.tamanho = tamanho;

	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public char getTamanho() {
		return tamanho;
	}

	public void setTamanho(char tamanho) {
		this.tamanho = tamanho;
	}
}
