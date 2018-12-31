package com.example.joanderson.bruxosbruxas.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joanderson.bruxosbruxas.R;
import com.example.joanderson.bruxosbruxas.adapter.AdapterCarrinho;
import com.example.joanderson.bruxosbruxas.model.Loja;

import java.util.NoSuchElementException;

public class CarrinhoActivity extends AppCompatActivity {

    Loja loja;
    RecyclerView recyclerView;
    Button botaoComprar;
    TextView total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_carrinho_);

            recyclerView = findViewById(R.id.rvCarrinho);
            total = findViewById(R.id.tvTotal_carrinho);

            if (getIntent().hasExtra("loja")) {
                loja = (Loja) getIntent().getSerializableExtra("loja");
                System.out.println("entrou no has extra");
            }
            else {
                throw new NoSuchElementException();
            }

            total.setText(loja.getCarrinho().getValorTotal().toString());
            System.out.println("Total é:" + loja.getCarrinho().getValorTotal().toString());

            AdapterCarrinho adapterCarrinho = new AdapterCarrinho(loja,total);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext());
            recyclerView.setLayoutManager(layoutManager);
            recyclerView.setHasFixedSize(true);
            recyclerView.setAdapter(adapterCarrinho);
            botaoComprar = findViewById(R.id.buttonComprar);
            botaoComprar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //todo: composição -- intent existe apenas enquanto esta classe existir
                    Intent intent = new Intent(getApplicationContext(), Login_Activity.class);
                    intent.putExtra("loja",loja);
                    startActivity(intent);
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
            builder.setMessage("Erro de conexao com a loja");
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
}
