package com.example.joanderson.bruxosbruxas.model.pagamento;
import com.example.joanderson.bruxosbruxas.model.Compra;


public abstract class Pagamento {

	//todo: método estático
	public static boolean efetuaPagamento(Banco banco, Compra compra) throws ClassNotFoundException {
		boolean realizado = false;

		//aqui ha uficam as instancias de todos os bancos
		if (banco instanceof BancoGringotes) {
			ClienteBanco clienteBanco = ((BancoGringotes) banco)
					.getCliente(compra.getCliente().getNome(), compra.getCliente().getVarinha());

			realizado = banco.debitar(clienteBanco,compra.getCarrinho().getValorTotal());
		}
		else {
			throw new ClassNotFoundException();
		}
		return realizado;
	}
	
}
