package com.example.joanderson.bruxosbruxas.model.pagamento;

import com.example.joanderson.bruxosbruxas.model.Dinheiro;

public interface Banco {
    //todo: interface

    boolean isExisteCliente(ClienteBanco clienteBanco);

    Dinheiro getSaldo(ClienteBanco clienteBanco);

    void depositar(ClienteBanco clienteBanco, Dinheiro valor);

    boolean debitar(ClienteBanco clienteBanco, Dinheiro valor);
}
