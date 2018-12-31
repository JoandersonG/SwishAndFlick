package com.example.joanderson.bruxosbruxas.model;

import com.example.joanderson.bruxosbruxas.model.produto.Produto;

import java.io.Serializable;
import java.util.ArrayList;

public class Carrinho implements Serializable {
	private ArrayList<Item> itens;
	private Dinheiro valorTotal;

	public Carrinho() {
		itens = new ArrayList<>();
		valorTotal = new Dinheiro(0,0,0);
	}

	public ArrayList<Item> getItens() {
		return itens;
	}

	public void addItem(Item item) {
		this.valorTotal.adicionarValor(item.getValor());
		for (Item i : itens) {
			if (i.getProduto().equals(item.getProduto())) {
				i.setQuantidade(i.getQuantidade() + item.getQuantidade());
				return;
			}
		}
		itens.add(item);
	}

	public void excluirItem(Item item) {

		this.itens.remove(item);
		this.valorTotal.subtrairValor(item.getValor());


	}

	public Dinheiro getValorTotal() {
		int g=0,s=0,n=0;
		Dinheiro d = new Dinheiro(0,0,0);
		for (Item i: itens) {
			d.adicionarValor(i.getValor());
//			g += i.getValor().getGaleao();
//			n += i.getValor().getNuque();
//			s += i.getValor().getSicle();
		}
//		this.valorTotal = new Dinheiro(g,s,n);
//		return this.valorTotal;
		this.valorTotal = d;
		return valorTotal;
	}

	public boolean contemProduto(Produto produto) {
		boolean teste = false;
		for (Item i : itens) {
			if (i.getProduto().equals(produto)) {
				teste = true;
			}
		}
		return teste;
	}

	public Item getItem(int indice) {
		return this.itens.get(indice);
	}




}
