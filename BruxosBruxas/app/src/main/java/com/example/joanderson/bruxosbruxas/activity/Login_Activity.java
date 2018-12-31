package com.example.joanderson.bruxosbruxas.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joanderson.bruxosbruxas.R;
import com.example.joanderson.bruxosbruxas.model.Compra;
import com.example.joanderson.bruxosbruxas.model.Loja;
import com.example.joanderson.bruxosbruxas.model.cliente.ClienteLoja;
import com.example.joanderson.bruxosbruxas.model.cliente.Varinha;
import com.example.joanderson.bruxosbruxas.model.pagamento.Pagamento;

import java.util.NoSuchElementException;

public class Login_Activity extends AppCompatActivity {

    private EditText nome,endereco,tamanhoVarinha,nucleoVarinha,madeiraVarinha;
    private Button buttonComprar,buttonCancelar;
    private Switch entregaTrouxa, autorizaGringotes;
    private TextView valorCompra;
    private Loja loja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login_);

            nome = findViewById(R.id.etNome);
            endereco = findViewById(R.id.etEndereco);
            entregaTrouxa = findViewById(R.id.switchTrouxas);
            tamanhoVarinha = findViewById(R.id.etTamanho);
            nucleoVarinha = findViewById(R.id.etNucleo);
            madeiraVarinha = findViewById(R.id.etMadeira);
            buttonComprar = findViewById(R.id.btComprar);
            buttonCancelar = findViewById(R.id.btCancelar);
            valorCompra = findViewById(R.id.tvValorFinal);
            autorizaGringotes = findViewById(R.id.switchGringotes);


            if (getIntent().hasExtra("loja")) {
                loja = (Loja) getIntent().getSerializableExtra("loja");
            }
            else {
                throw new NoSuchElementException();
            }

            String val = "Valor da compra: " + loja.getCarrinho().getValorTotal().toString();
            valorCompra.setText(val);

            buttonCancelar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                    startActivity(intent);
                    finish();
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
        catch (ClassCastException e1) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Erro interno. Nao foi possivel recuperar informaçoes da loja");
            AlertDialog a = builder.create();
            a.show();
            e1.printStackTrace();
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

    public void comprar(View view) {
        try {
            if (testeComprar()) {

                if (!autorizaGringotes.isChecked()) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(this);
                    builder.setMessage("Por favor, autorize o débito em sua conta do Banco Gringotes");
                    AlertDialog a = builder.create();
                    a.show();
                    return;
                }

                ClienteLoja cliente = new ClienteLoja(
                        nome.getText().toString(),
                        endereco.getText().toString(),
                        new Varinha(Double.parseDouble(tamanhoVarinha.getText().toString()),
                                madeiraVarinha.getText().toString(),
                                nucleoVarinha.getText().toString())
                );
                Compra compra = new Compra(loja.getCarrinho(), cliente, entregaTrouxa.isActivated());
                //Pagamento pagamento = new Pagamento(compra);

                boolean pagamentoRealizado = Pagamento.efetuaPagamento(loja.getBancosDisponiveis().get(0), compra);
                ;

                Intent intent = new Intent(getApplicationContext(), CompraConcluidaActivity.class);
                intent.putExtra("loja", loja);
                if (pagamentoRealizado) {
                    loja.adicionarCompraRealizada(compra);
                }
                intent.putExtra("statusPagamento", pagamentoRealizado);
                startActivity(intent);

            } else {
                Toast.makeText(this, "Por favor, preencha todos os campos",
                        Toast.LENGTH_LONG).show();

            }
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

        public boolean testeComprar() {
            boolean teste = true;
            try {

                if (nome.getText().toString().equals("")
                        || endereco.getText().toString().equals("")
                        || tamanhoVarinha.getText().toString().equals("")
                        || nucleoVarinha.getText().toString().equals("")
                        || madeiraVarinha.getText().toString().equals("")
                        ) {
                    teste = false;
                }
            }
            catch (Exception e2) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Erro desconhecido");
                AlertDialog a = builder.create();
                a.show();
                e2.printStackTrace();
                finish();
            }
            return teste;
        }

        public void leituraEt(View view) {
            try {
                ((EditText) view).setHint("");
                ((EditText) view).setTextColor(Color.rgb(0, 0, 0));
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

}