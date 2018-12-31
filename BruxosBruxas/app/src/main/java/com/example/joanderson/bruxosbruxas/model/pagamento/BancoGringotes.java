package com.example.joanderson.bruxosbruxas.model.pagamento;

import com.example.joanderson.bruxosbruxas.model.Dinheiro;
import com.example.joanderson.bruxosbruxas.model.cliente.Varinha;

import java.io.Serializable;
import java.util.ArrayList;


public class BancoGringotes implements Banco, Serializable {
	//todo: campo estático
	private static ArrayList<ClienteBanco> contasGringotes = new ArrayList<>();
	//private Pagamento pagamento;
	
	public static void addCliente(ClienteBanco cliente) {
		contasGringotes.add(cliente);
	}
	
	public static void removerCliente(ClienteBanco cliente) {
		contasGringotes.remove(cliente);
	}


	public ClienteBanco getCliente(String nome, Varinha varinha) {
		ClienteBanco c = null;
		for ( ClienteBanco cli : contasGringotes) {
			if (cli.getVarinha().equals(varinha) && nome.equals(cli.getNome())) {
				c = cli;
			}
		}
		return c;
	}

	@Override
	public boolean isExisteCliente(ClienteBanco clienteBanco) {
		boolean teste = false;
		for ( ClienteBanco cli : contasGringotes) {
			if (cli.equals(clienteBanco)) {
				teste = true;
				break;
			}
		}
		return teste;
	}

	@Override
	public Dinheiro getSaldo(ClienteBanco clienteBanco) {
		Dinheiro d = null;
		if (isExisteCliente(clienteBanco)) {
			d = clienteBanco.getSaldoConta();
		}
		return d;
	}

	@Override
	public void depositar(ClienteBanco clienteBanco, Dinheiro valor) {
		if (isExisteCliente(clienteBanco)) {
			clienteBanco.getSaldoConta().adicionarValor(valor);
		}
	}

	@Override
	public boolean debitar(ClienteBanco clienteBanco, Dinheiro valor) {
		boolean teste = false;
		if (isExisteCliente(clienteBanco) && clienteBanco.getSaldoConta().isMaiorQue(valor)) {
			clienteBanco.getSaldoConta().subtrairValor(valor);
			teste = true;
		}

		return teste;
	}

}
