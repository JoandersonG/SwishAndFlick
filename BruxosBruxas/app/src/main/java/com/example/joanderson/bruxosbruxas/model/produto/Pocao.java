package com.example.joanderson.bruxosbruxas.model.produto;

import com.example.joanderson.bruxosbruxas.model.Dinheiro;

public class Pocao extends Produto {
	private String efeitoColateral;
	private String contraIndicacao;
	
	public Pocao(String nome, String descricao, Dinheiro preco, int estoque, int imagem, String efeitoColateral,
				 String contraIndicacao) {
		super(nome, descricao, preco, estoque, imagem);
		this.efeitoColateral = efeitoColateral;
		this.contraIndicacao = contraIndicacao;
	}

	public String getEfeitoColateral() {
		return efeitoColateral;
	}

	public void setEfeitoColateral(String efeitoColateral) {
		this.efeitoColateral = efeitoColateral;
	}

	public String getContraIndicacao() {
		return contraIndicacao;
	}

	public void setContraIndicacao(String contraIndicacao) {
		this.contraIndicacao = contraIndicacao;
	}
	
	
	
	
	
	

}
