package com.example.guilhermesborz.avaliacaoalimentos.Adapter;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.guilhermesborz.avaliacaoalimentos.Modelo.Gastronomia;
import com.example.guilhermesborz.avaliacaoalimentos.R;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Micro Solution on 07/12/2016.
 */

public class GastronomiaAdapter extends RecyclerView.Adapter{

    private ArrayList<Gastronomia> listaGastronomia;
    private Context context;
    Date dataAtual = new Date();

    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yy");
    String dataFormatada = sdf.format(dataAtual);

    public GastronomiaAdapter(Context c, ArrayList<Gastronomia> p){
        this.listaGastronomia = p;
        this.context = c;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.itens_lista,parent,false);
        GastronomiaViewHolder retorno = new GastronomiaViewHolder(view);
        return retorno;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        GastronomiaViewHolder elemento = (GastronomiaViewHolder) holder;
        Gastronomia ab = listaGastronomia.get(position);
        elemento.seAtualiza(ab);
    }

    @Override
    public int getItemCount() {
        return this.listaGastronomia.size();
    }
    public class GastronomiaViewHolder extends  RecyclerView.ViewHolder{

        private ImageView ivFotoComida;
        private TextView tvTipoComida;
        private TextView tvDescricaoLista;
        private TextView tvNotaRatingBar;


        public GastronomiaViewHolder(View itemView) {
            super(itemView);

            this.ivFotoComida = (ImageView)itemView.findViewById(R.id.ivFotoComida);
            this.tvDescricaoLista = (TextView)itemView.findViewById(R.id.tvDescricaoItemLista);
            this.tvTipoComida = (TextView)itemView.findViewById(R.id.tvTipoComida);
            this.tvNotaRatingBar = (TextView)itemView.findViewById(R.id.tvNotaRatingBar);
        }

        public void seAtualiza(Gastronomia itemParaMostrar){
            tvTipoComida.setText(itemParaMostrar.getCategoria());
            tvDescricaoLista.setText(itemParaMostrar.getDescricao());
            tvNotaRatingBar.setText("Avaliação "+itemParaMostrar.getNotaRatingBar()+" no dia "+dataFormatada);
            ivFotoComida.setImageBitmap(itemParaMostrar.getImagemCapturada());
        }
    }
}