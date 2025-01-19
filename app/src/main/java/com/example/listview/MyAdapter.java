package com.example.listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<String> nombres;
    private ArrayList<Integer> imagenes;

    public MyAdapter(Context context, int layout, ArrayList<String> nombres, ArrayList<Integer> imagenes){
    this.context = context;
    this.layout = layout;
    this.nombres = nombres;
    this.imagenes = imagenes;

    }

    @Override
    public int getCount() {
        return this.nombres.size();
    }

    @Override
    public Object getItem(int position) {
        return this.nombres.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = convertView;

        //inflamos la vista con nuestro propio layout
        LayoutInflater layoutInflater = LayoutInflater.from(this.context);

        v = layoutInflater.inflate(R.layout.list_item, null);

        //valor actual de la posicion
        String nombreActual = nombres.get(position);
        int imagenActual = imagenes.get(position);

        TextView textoElemento = v.findViewById(R.id.texto_elemento);
        textoElemento.setText(nombreActual);

        ImageView imagenElemento = v.findViewById(R.id.imagen_elemento);
        imagenElemento.setImageResource(imagenActual);

        return v;
    }
}
