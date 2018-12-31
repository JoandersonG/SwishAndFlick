package com.example.joanderson.bruxosbruxas.model.produto;

import com.example.joanderson.bruxosbruxas.model.Dinheiro;

public class Livro extends Produto{
	private int qtdPaginas;
	private String genero;

	public Livro(String nome, String descricao, Dinheiro preco, int estoque, int imagem, int qtdPaginas, String genero) {
		super(nome, descricao, preco, estoque, imagem);
		if (genero == null || qtdPaginas <= 0) {
			throw new IllegalArgumentException();
		}
		this.qtdPaginas = qtdPaginas;
		this.genero = genero;
	}

	public int getQtdPaginas() {
		return qtdPaginas;
	}

	public void setQtdPaginas(int qtdPaginas) {
		this.qtdPaginas = qtdPaginas;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}
}
