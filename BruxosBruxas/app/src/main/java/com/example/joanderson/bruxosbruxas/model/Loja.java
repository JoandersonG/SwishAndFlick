package com.example.joanderson.bruxosbruxas.model;

import com.example.joanderson.bruxosbruxas.model.pagamento.Banco;
import com.example.joanderson.bruxosbruxas.model.produto.Produto;

import java.io.Serializable;
import java.util.ArrayList;

public class Loja implements Serializable {
    private Carrinho carrinho;
    private ArrayList <Produto> produtos;
    private ArrayList <Compra> comprasRealizadas;
    private ArrayList <Banco> bancosDisponiveis;//atualmente apenas um

    public Loja(ArrayList<Banco> bancosDisponiveis) {

        if (bancosDisponiveis == null) {
            throw new IllegalArgumentException();
        }

        this.carrinho = new Carrinho();
        this.produtos = new ArrayList<>();
        this.comprasRealizadas = new ArrayList<>();
        this.bancosDisponiveis = bancosDisponiveis;
    }
    public Loja(Banco bancoDisponivel) {

        if (bancoDisponivel == null) {
            throw new IllegalArgumentException();
        }

        this.carrinho = new Carrinho();
        this.produtos = new ArrayList<>();
        this.comprasRealizadas = new ArrayList<>();
        this.bancosDisponiveis = new ArrayList<>();
        this.bancosDisponiveis.add(bancoDisponivel);
    }

    public void adicionarBanco(Banco banco) {
        this.bancosDisponiveis.add(banco);
    }

    public void removerBanco(Banco banco) {
        for (Banco a : bancosDisponiveis) {
            if (a.getClass() == banco.getClass()) {
                bancosDisponiveis.remove(a);
                break;
            }
        }
    }

    public void adicionarCompraRealizada(Compra compra) {
        if (compra != null) comprasRealizadas.add(compra);
        else throw new IllegalArgumentException();
    }
    public ArrayList<Compra> getComprasRealizadas() {
        return comprasRealizadas;
    }

    public void setComprasRealizadas(ArrayList<Compra> comprasRealizadas) {
        this.comprasRealizadas = comprasRealizadas;
    }

    public ArrayList<Banco> getBancosDisponiveis() {
        return bancosDisponiveis;
    }

    public void setBancosDisponiveis(ArrayList<Banco> bancosDisponiveis) {
        this.bancosDisponiveis = bancosDisponiveis;
    }

    public void adicionarProduto(Produto produto) {
        produtos.add(produto);
    }

    public void adicionarAoCarrinho(Produto produto, int quantidade) {
        carrinho.addItem(new Item(produto,quantidade));
    }

    public Carrinho getCarrinho() {
        return carrinho;
    }

    public void setCarrinho(Carrinho carrinho) {
        this.carrinho = carrinho;
    }

    public ArrayList<Produto> getProdutos() {
        return produtos;
    }

    public void setProdutos(ArrayList<Produto> produtos) {
        this.produtos = produtos;
    }
}
