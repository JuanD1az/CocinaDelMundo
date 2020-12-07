package com.app.cocinadelmundo;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter2 extends RecyclerView.Adapter<Adapter2.ViewHolderDatos> implements View.OnClickListener {

    ArrayList<RecetasOnline> listDatos;

    private View.OnClickListener listener;

    public Adapter2(ArrayList<RecetasOnline> listDatos) {
        this.listDatos = listDatos;
    }

    @Override
    public ViewHolderDatos onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list,null,false);

        view.setOnClickListener(this);

        return new ViewHolderDatos(view);
    }

    @Override
    public void onBindViewHolder(ViewHolderDatos holder, int position) {
        holder.dato.setText(listDatos.get(position).getNombre());
        holder.pais.setText("Codigo: "+listDatos.get(position).getCodigo());
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

        TextView dato,pais;

        public ViewHolderDatos(View itemView) {
            super(itemView);
            dato=(TextView) itemView.findViewById(R.id.idDato);
            pais=(TextView) itemView.findViewById(R.id.idDatoPais);
        }
    }
}