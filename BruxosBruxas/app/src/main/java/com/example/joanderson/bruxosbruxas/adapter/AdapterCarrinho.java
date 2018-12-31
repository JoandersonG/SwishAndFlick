package com.example.joanderson.bruxosbruxas.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.joanderson.bruxosbruxas.R;
import com.example.joanderson.bruxosbruxas.model.Item;
import com.example.joanderson.bruxosbruxas.model.Loja;

import java.util.ArrayList;

public class AdapterCarrinho  extends RecyclerView.Adapter<AdapterCarrinho.MyViewHolder> {

    private Loja loja;
    private TextView total;
    public AdapterCarrinho(Loja loja, TextView total) {
        this.loja = loja;
        this.total = total;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {

        View itemCarrinho = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.adapter_carrinho, viewGroup, false);
        return new MyViewHolder(itemCarrinho);

    }


    @Override
    public void onBindViewHolder(final MyViewHolder myViewHolder, final int i) {

        Item it = loja.getCarrinho().getItens().get(i);

        myViewHolder.titulo.setText(it.getProduto().getNome());

        myViewHolder.descricaoResumida.setText(it.getProduto().getDescricao());

        myViewHolder.quantidade.setText(String.valueOf(it.getQuantidade()));

        myViewHolder.imagem.setImageResource(it.getProduto().getImagem());

        total.setText(loja.getCarrinho().getValorTotal().toString());

        myViewHolder.btAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loja.getCarrinho().getItem(i).setQuantidade(loja.getCarrinho().getItem(i).getQuantidade() + 1);
                myViewHolder.quantidade.setText(String.valueOf(loja.getCarrinho().getItem(i).getQuantidade()));
                total.setText(loja.getCarrinho().getValorTotal().toString());
                //notifyAll();
            }
        });


        myViewHolder.btSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                subtraiCarrinho(i,myViewHolder);
            }
        });

        //noã conseguimos implementar
//        myViewHolder.btRemove.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
////                loja.getCarrinho().excluirItem(loja.getCarrinho().getItem(i));
////                notifyItemRemoved(myViewHolder.getAdapterPosition());
////                notifyItemRangeChanged(myViewHolder.getAdapterPosition(),loja.getCarrinho().getItens().size()-1);
//
//            }
//        });




    }

    @Override
    public int getItemCount() {
        return loja.getCarrinho().getItens().size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imagem;
        TextView titulo, descricaoResumida,quantidade;
        ImageButton btAdd,btSub,btRemove;

        public MyViewHolder(View itemView) {
            super(itemView);

            imagem = itemView.findViewById(R.id.imageProduto);
            titulo = itemView.findViewById(R.id.tvTitulo);
            descricaoResumida = itemView.findViewById(R.id.tvDescricao);
            quantidade = itemView.findViewById(R.id.tvQtd);
            btAdd = itemView.findViewById(R.id.ibAdd);
            btSub = itemView.findViewById(R.id.ibSub);
            btRemove = itemView.findViewById(R.id.ibTrash);

        }

    }


    public void subtraiCarrinho(int i, MyViewHolder myViewHolder) {
        if (loja.getCarrinho().getItem(i).getQuantidade() > 1) {

            loja.getCarrinho().getItem(i).setQuantidade(loja.getCarrinho().getItem(i).getQuantidade() - 1);
            myViewHolder.quantidade.setText(String.valueOf(loja.getCarrinho().getItem(i).getQuantidade()));
            total.setText(loja.getCarrinho().getValorTotal().toString());
            //notifyAll();

        }
    }


}
