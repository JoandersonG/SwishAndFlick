package com.example.joanderson.bruxosbruxas.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.joanderson.bruxosbruxas.R;
import com.example.joanderson.bruxosbruxas.model.Loja;

import java.util.NoSuchElementException;

public class CompraConcluidaActivity extends AppCompatActivity {

    TextView tvStatusPedido, tvDescricao;
    Button buttonVoltarLoja;
    boolean statusCompra;
    Loja loja;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_compra_concluida);

            tvStatusPedido = findViewById(R.id.tvCompraStatus);
            tvDescricao = findViewById(R.id.tvTextoApoio);
            buttonVoltarLoja = findViewById(R.id.buttonVoltarLoja);

            if (!getIntent().hasExtra("statusPagamento") || !getIntent().hasExtra("loja")) {
                throw new NoSuchElementException();
            }
            statusCompra = getIntent().getBooleanExtra("statusPagamento", false);
            loja = (Loja) getIntent().getSerializableExtra("loja");
            if (statusCompra) {
                setCompraRealizada();
            } else {
                setCompraNaoRealizada();
            }

            buttonVoltarLoja.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (statusCompra) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("loja", loja);
                        startActivity(intent);
                    } else {
                        finish();
                    }

                }
            });

        }
        catch (NoSuchElementException e0) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Erro de conexao com a loja");
            AlertDialog a = builder.create();
            a.show();
            e0.printStackTrace();
            finish();
        }
        catch (Exception e2) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Erro desconhecido");
            AlertDialog a = builder.create();
            a.show();
            e2.printStackTrace();
            finish();
        }
    }

    public void setCompraRealizada() {
        String mainTexto = "COMPRA REALIZADA COM SUCESSO";
        tvStatusPedido.setText(mainTexto);
        String comp = " Em até dois dias sua compra chegará em sua casa." +
                "\nAproveite e continue comprando";
        tvDescricao.setText(comp);
    }

    public void setCompraNaoRealizada() {
        String mainTexto = "COMPRA NÃO REALIZADA";
        tvStatusPedido.setText(mainTexto);
        String comp = "Houve algum problema com suas informações ou seu saldo no Gringotes é insuficiente.";
        tvDescricao.setText(comp);

        buttonVoltarLoja.setText("   Tentar novamente   ");
    }
}
