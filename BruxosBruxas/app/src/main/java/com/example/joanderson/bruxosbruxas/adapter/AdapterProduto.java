package com.example.joanderson.bruxosbruxas.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.joanderson.bruxosbruxas.R;
import com.example.joanderson.bruxosbruxas.activity.DetalhesProdutoActivity;
import com.example.joanderson.bruxosbruxas.model.Loja;

public class AdapterProduto extends RecyclerView.Adapter<AdapterProduto.MyViewHolder> {

    private Context context;
    private Loja loja;

    public AdapterProduto(Context context, Loja loja) {
        this.context = context;
        this.loja = loja;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {

        View produto = LayoutInflater.from(viewGroup.getContext()).
                inflate(R.layout.adapter_produto,viewGroup,false);

        return new MyViewHolder(produto);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder myViewHolder,final int i) {

        myViewHolder.tvTituloProduto.setText(loja.getProdutos().get(i).getNome());
        myViewHolder.ivProduto.setImageResource(loja.getProdutos().get(i).getImagem());

        myViewHolder.buttonComprarProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,myViewHolder.tvTituloProduto.getText().toString() + " adicionado ao carrinho",Toast.LENGTH_SHORT).show();
                //Snackbar.make(v, "Produto adicionado ao carrinho", Snackbar.LENGTH_SHORT).show();
                loja.adicionarAoCarrinho(loja.getProdutos().get(i),1);

            };
        });
        myViewHolder.ivProduto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetalhesProdutoActivity.class);
                intent.putExtra("produto",loja.getProdutos().get(i));
                intent.putExtra("loja", loja);
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return loja.getProdutos().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView ivProduto;
        TextView tvTituloProduto;
        Button buttonComprarProd;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            ivProduto = itemView.findViewById(R.id.ivProduto);
            tvTituloProduto = itemView.findViewById(R.id.tvTituloProduto);
            buttonComprarProd = itemView.findViewById(R.id.buttonComprarProd);

        }
    }
}
