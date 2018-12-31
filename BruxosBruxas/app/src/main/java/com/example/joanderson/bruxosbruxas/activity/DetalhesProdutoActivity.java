package com.example.joanderson.bruxosbruxas.activity;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joanderson.bruxosbruxas.R;
import com.example.joanderson.bruxosbruxas.model.Carrinho;
import com.example.joanderson.bruxosbruxas.model.Item;
import com.example.joanderson.bruxosbruxas.model.produto.Livro;
import com.example.joanderson.bruxosbruxas.model.Loja;
import com.example.joanderson.bruxosbruxas.model.produto.Pocao;
import com.example.joanderson.bruxosbruxas.model.produto.Produto;
import com.example.joanderson.bruxosbruxas.model.produto.Utensilio;
import com.example.joanderson.bruxosbruxas.model.produto.Vestimenta;

import java.util.NoSuchElementException;


public class DetalhesProdutoActivity extends AppCompatActivity {

    ImageView ivProduto;
    TextView titulo, detalhes, preco;
    Produto produto;
    Button botaoAddCarrinho;
    Loja loja;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {//todo: try e catch e throw exception
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_detalhes_produto);

            ivProduto = findViewById(R.id.iv_produto_detalhes);
            titulo = findViewById(R.id.tvTitulo_detalhes);
            detalhes = findViewById(R.id.tvDetalhes_detalhes);
            preco = findViewById(R.id.tvPreco_detalhes);
            botaoAddCarrinho = findViewById(R.id.buttonAddCarrinho_detalhes);

            if (getIntent().hasExtra("produto")) {

                produto = (Produto) getIntent().getSerializableExtra("produto");

                ivProduto.setImageResource(produto.getImagem());
                titulo.setText(produto.getNome());
                detalhes.setText(descricaoCompleta(produto));

                String p = preco.getText().toString() + produto.getPreco().toString();
                preco.setText(p);
            }
            else {
                throw new NoSuchElementException();
            }
            if (getIntent().hasExtra("loja")) {
                loja = (Loja) getIntent().getSerializableExtra("loja");
            }
            else {
                throw new NoSuchElementException();
            }

            botaoAddCarrinho.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getApplicationContext(), produto.getNome() +
                            " adicionado ao carrinho", Toast.LENGTH_SHORT).show();

                    loja.adicionarAoCarrinho(produto, 1);
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
        catch (ClassNotFoundException e1) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Erro: produto incompativel encontrado na loja");
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

    public String descricaoCompleta(Produto produto) throws ClassNotFoundException {
        String desc = produto.getDescricao();
        //todo: cast e instance of
        if (produto instanceof Livro) {
            Livro livro = (Livro) produto;
            desc += "\nGênero: " + livro.getGenero() +
                    "\nQuantidade de páginas: " + String.valueOf(livro.getQtdPaginas());
        }
        else if (produto instanceof Pocao) {
            Pocao pocao = (Pocao) produto;
            desc += "\nContra indicações: " + pocao.getContraIndicacao() +
                    "\nEfeitos colaterais: " + pocao.getEfeitoColateral();
        }
        else if (produto instanceof Utensilio) {
            Utensilio utensilio = (Utensilio) produto;
            desc += "\nCategoria: " + utensilio.getCategoria();
        }
        else if (produto instanceof Vestimenta) {
            Vestimenta vestimenta = (Vestimenta) produto;
            desc += "\nTamanho: " + vestimenta.getTamanho();
        }
        else {
            throw new ClassNotFoundException();
        }

        return desc;
    }
}
