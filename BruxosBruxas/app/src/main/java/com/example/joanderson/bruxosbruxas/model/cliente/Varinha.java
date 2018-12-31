package com.example.joanderson.bruxosbruxas.model.cliente;

import java.io.Serializable;

public class Varinha implements Serializable {
	private double tamanho;
	private String madeira;
	private String nucleo;
	
	public Varinha(double tamanho, String madeira, String nucleo) {
		if (tamanho == 0 || madeira == null || nucleo == null) {
			throw new IllegalArgumentException();
		}
		this.tamanho = tamanho;
		this.madeira = madeira;
		this.nucleo = nucleo;
	}

	public double getTamanho() {
		return tamanho;
	}

	public void setTamanho(double tamanho) {
		this.tamanho = tamanho;
	}

	public String getMadeira() {
		return madeira;
	}

	public void setMadeira(String madeira) {
		this.madeira = madeira;
	}

	public String getNucleo() {
		return nucleo;
	}

	public void setNucleo(String nucleo) {
		this.nucleo = nucleo;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Varinha varinha = (Varinha) o;
		return Double.compare(varinha.tamanho, tamanho) == 0 &&
				madeira.equals(varinha.madeira) &&
				nucleo.equals(varinha.nucleo);
	}

}
