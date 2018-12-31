package com.example.joanderson.bruxosbruxas.model.produto;

import com.example.joanderson.bruxosbruxas.model.Dinheiro;

public class Utensilio extends Produto {
	private String categoria;

	public Utensilio(String nome, String descricao, Dinheiro preco, int estoque, int imagem, String categoria) {
		super(nome, descricao, preco, estoque, imagem);
		if (categoria == null) {
			throw new IllegalArgumentException();
		}
		this.categoria = categoria;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	
	

}
