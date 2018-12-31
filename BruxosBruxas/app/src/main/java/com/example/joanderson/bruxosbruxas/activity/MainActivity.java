package com.example.joanderson.bruxosbruxas.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.example.joanderson.bruxosbruxas.R;
import com.example.joanderson.bruxosbruxas.adapter.AdapterProduto;
import com.example.joanderson.bruxosbruxas.model.Dinheiro;
import com.example.joanderson.bruxosbruxas.model.Loja;
import com.example.joanderson.bruxosbruxas.model.cliente.Varinha;
import com.example.joanderson.bruxosbruxas.model.pagamento.BancoGringotes;
import com.example.joanderson.bruxosbruxas.model.pagamento.ClienteBanco;
import com.example.joanderson.bruxosbruxas.model.produto.Livro;
import com.example.joanderson.bruxosbruxas.model.produto.Pocao;
import com.example.joanderson.bruxosbruxas.model.produto.Utensilio;
import com.example.joanderson.bruxosbruxas.model.produto.Vestimenta;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerProduto;
    private ImageView ivCarrinho;
    private FloatingActionButton fab;
    private Loja loja;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        try {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_main);

            recyclerProduto = findViewById(R.id.rvProdutos);
            fab = findViewById(R.id.fabCarrinho);

            if (getIntent().hasExtra("loja")) {
                loja = (Loja) getIntent().getSerializableExtra("loja");
            }
            else {
                loja = new Loja(new BancoGringotes());
                adicionarProdutos();
            }

            fab.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(), CarrinhoActivity.class);
                    intent.putExtra("loja", loja);
                    startActivity(intent);
                }
            });

            //definir layout
            RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 2);
            recyclerProduto.setLayoutManager(layoutManager);
            recyclerProduto.setHasFixedSize(true);
            //definir adapter
            AdapterProduto adapter = new AdapterProduto(this, loja);

            recyclerProduto.setAdapter(adapter);
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

    private void adicionarProdutos() {
        try {

            Vestimenta chapeu1 = new Vestimenta("Chapéu Pomodora",
                    "Chapéu de bico curvo, afivelado, encantado com o feitiço Impervius, " +
                            "e portanto capaz de repelir água.\n" +
                            "Nunca mais se preocupe em se molhar numa chuva repentina " +
                            "- ao menos sua cabeça, garantimos seca.",
                    new Dinheiro(4, 10, 0), 10, R.drawable.chapeu_1, "chapéu",'U');

            Utensilio caldeirao1 = new Utensilio("Caldeirão de Estanho",
                    "Caldeirão de fundo grosso com 15 cm de diâmetro.\n" +
                            "Perfeitamente capaz de preparar diversas poções, " +
                            "incluíndo todas as poções do Livro Avançado em Poções.",
                    new Dinheiro(2, 0, 0), 20, R.drawable.caldeirao_um, "escolar");

            Livro livro1 = new Livro("Animais Fantáticos e Onde Habitam",
                    "Do notável Newt Scamander, um dos maiores bestiários da história" +
                            ". Em suas páginas, este livro detalha dezenas de animais mágicos com " +
                            "uma enorme clareza e riqueza em detalhes.",
                    new Dinheiro(2,3,0),20,R.drawable.livro4,340,"Não-Ficção");

            Livro livro2 = new Livro("Os Contos de Beedle, o Bardo",
                    "Grandes histórias infantis, escritas pelo mítico e lendário Beedle. " +
                            "Esta coletânea de contos é indispensável na criação de qualquer " +
                            "criança bruxa.\nDivirta-se contando essas incríveis histórias a suas crianças.",
                    new Dinheiro(1,5,0),20,R.drawable.livro5,120,"Ficção infantil");

            Livro livro3 = new Livro("Advanced Potion Making",
                    "Advanced Potion-Making is a book written by Libatius Borage. " +
                            "As the title implies this book contains advanced recipes and various" +
                            " other topics related to potion-making. This textbook has been used for" +
                            " decades in the education of young witches and wizards.",
                    new Dinheiro(7,10,0),20,R.drawable.livro9,360,"Não-Ficção");

            Vestimenta vestido1 = new Vestimenta("Vestido Estrelado",
                    "Vestido leve, feito de tecidos finos pela Madame Malkin, possui em" +
                            "seus tecidos um tratamento mágico para que seja ainda mais leve.",
                    new Dinheiro(0,20,5),5,R.drawable.vestido1,"Vestido",'m');

            Vestimenta roupaInfantil = new Vestimenta("Veste Infantil para Festas",
                    "Capa e chapéu infantis, ideais para festas e ocasiões especiais." +
                            "Não acompanha demais objetos.",
                    new Dinheiro(1,0,0),5,R.drawable.infantil,"Infantil",'p');

            Pocao pocao1 = new Pocao("Poção do Morto-Vivo",
                    "500 ml. A Poção do Morto-Vivo Merlin's Potion faz com que aquele que a beber mergulhe " +
                            "num sono profundo. É a poção de sono mais poderosa que existe. " +
                            "Não use com frequência maior que duas vezes por mês."
                            ,new Dinheiro(10,17,0),5,R.drawable.pocao7,
                            "Sonolência e falta de aptidão",
                    "Na dosagem errada pode levar a um sono intermitente");

            Pocao pocao2 = new Pocao("Poção Polissuco Merlin's Potion",
                    "500 ml.  Permite que o consumidor assuma temporariamente a aparência física de" +
                            " outra pessoa. Necessária alguma parte do corpo desse " +
                            "indivíduo, em geral, fio de cabelo" +
                            ". Merlin's Potion garante uma hora de transformação."
                    ,new Dinheiro(15,0,0),3,R.drawable.pocao3,
                    "Nenhum efeito colateral.",
                    "Não é permitida a transformação entre espécies.");

            Pocao pocao3 = new Pocao("Poção Wiggenweld",
                    "400 ml. Tem o poder que restaurar as forças de uma pessoa que está" +
                            " demasiada fraca por estar doente, por ter se machucado ou simplesmente" +
                            " por estar muito cansado."
                    ,new Dinheiro(1,10,0),3,R.drawable.pocao2,
                    "Nenhum efeito colateral.",
                    "Sem contra-indicações.");

            Utensilio lembrol = new Utensilio("Lembrol HardSkull",
                    "Bola de vidro do tamanho de uma grande bola de gude" +
                            " cheia de fumaça branca, que se torna escarlate caso a pessoa que a " +
                            "segura tenha se esquecido de alguma coisa. 12 cm de diâmetro",
                    new Dinheiro(1,12,0),15,R.drawable.lembrol,
                    "Objetos mágicos");

            Utensilio xadrez1 = new Utensilio("Xadrez encantado","Xadrez feito em" +
                    "metal e encantado. Basta dizer o movimento para que as peças avancem. Após as" +
                    " partidas, todas as peças se reintegram.", new Dinheiro(10,0,0),
                    7,R.drawable.xadrez1,"Diversão mágica");

            Vestimenta vesteHogwarts1 = new Vestimenta("Veste Padrão Hogwarts casa Gryffindor",
                    "Acompanha gravata.\nCom logo da Casa costurada, é produzida pela" +
                            " Madame Malkin, o que garante máxima qualidade.\nNão acompanha demais objetos.",
                    new Dinheiro(4,0,0),5,R.drawable.tunica1,"Hogwarts",'p');

            Vestimenta vesteHogwarts2 = new Vestimenta("Veste Padrão Hogwarts casa Ravenclaw",
                    "Acompanha gravata.\nCom logo da Casa costurada, é produzida pela" +
                            " Madame Malkin, o que garante máxima qualidade.\nNão acompanha demais objetos.",
                    new Dinheiro(4,0,0),5,R.drawable.tunica3,"Hogwarts",'p');

            Vestimenta vesteHogwarts3 = new Vestimenta("Veste Padrão Hogwarts casa Slytherin",
                    "Acompanha gravata.\nCom logo da Casa costurada, é produzida pela" +
                            " Madame Malkin, o que garante máxima qualidade.\nNão acompanha demais objetos.",
                    new Dinheiro(4,0,0),5,R.drawable.tunica4,"Hogwarts",'p');

            Vestimenta vesteHogwarts4 = new Vestimenta("Veste Padrão Hogwarts casa Hufflepuff",
                    "Acompanha gravata.\nCom logo da Casa costurada, é produzida pela" +
                            " Madame Malkin, o que garante máxima qualidade.\nNão acompanha demais objetos.",
                    new Dinheiro(4,0,0),5,R.drawable.tunica2,"Hogwarts",'p');

            //todo: polimorfismo|| loja.adicionarProduto() recebe um objeto Produto
            loja.adicionarProduto(chapeu1);
            loja.adicionarProduto(caldeirao1);
            loja.adicionarProduto(livro1);
            loja.adicionarProduto(livro2);
            loja.adicionarProduto(livro3);
            loja.adicionarProduto(vestido1);
            loja.adicionarProduto(roupaInfantil);
            loja.adicionarProduto(pocao1);
            loja.adicionarProduto(pocao2);
            loja.adicionarProduto(pocao3);
            //loja.adicionarProduto(lembrol);
            loja.adicionarProduto(xadrez1);
            loja.adicionarProduto(vesteHogwarts1);
            loja.adicionarProduto(vesteHogwarts2);
            loja.adicionarProduto(vesteHogwarts3);
            loja.adicionarProduto(vesteHogwarts4);

            BancoGringotes.addCliente(new ClienteBanco("a",
                    new Dinheiro(20,0,0),
                    new Varinha(1,"a","a")));

            //loja.adicionarAoCarrinho(caldeirao1,2);

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
