package com.example.examenfinal.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.examenfinal.R;
import com.example.examenfinal.modelo.Producto;

import java.util.List;

public class ProductoAdapter extends BaseAdapter {
    private Context context;
    private List<Producto> lista;
    public ProductoAdapter(Context context, List<Producto> model){
        this.context = context;
        this.lista = model;
    }
    @Override
    public int getCount() {
        return lista.size();
    }

    @Override
    public Object getItem(int i) {
        return lista.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Producto producto = lista.get(i);
        if(view == null){
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.productos, null);
        }
        TextView txtNom = (TextView) view.findViewById(R.id.producto_lista_nombre);
        txtNom.setText((i+1)+".- "+producto.getNombres()+" -> "+producto.getPrecio()+" -> " +producto.getStock());

        return view;
    }
}
