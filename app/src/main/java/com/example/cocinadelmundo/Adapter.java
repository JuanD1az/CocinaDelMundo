package com.example.cocinadelmundo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolderDatos> implements View.OnClickListener {

    ArrayList<Receta> listDatos;
    private View.OnClickListener listener;

    public Adapter(ArrayList<Receta> listDatos) {
        this.listDatos = listDatos;
    }

    @Override
    public ViewHolderDatos onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cantante,null,false);

        view.setOnClickListener(this);

        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderDatos holder, int position) {
        holder.etreceta.setText(listDatos.get(position).getNombre());
        holder.etcodigo.setText("Codigo: "+listDatos.get(position).getCodigo());


    }

    @Override
    public int getItemCount() {
        return listDatos.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener=listener;
    }

    @Override
    public void onClick(View view) {
        if(listener!=null){
            listener.onClick(view);
        }
    }

    public class ViewHolderDatos extends RecyclerView.ViewHolder {

        TextView etreceta;
        TextView etcodigo;

        public ViewHolderDatos(View itemView) {
            super(itemView);
            etreceta = (TextView) itemView.findViewById(R.id.receta);
            etcodigo = (TextView) itemView.findViewById(R.id.codigo);
        }

    }
}