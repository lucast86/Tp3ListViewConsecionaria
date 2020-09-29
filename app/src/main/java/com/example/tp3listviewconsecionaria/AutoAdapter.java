package com.example.tp3listviewconsecionaria;

import android.content.ClipData;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.tp3listviewconsecionaria.models.Autos;

import java.util.List;

public class AutoAdapter extends BaseAdapter {

    private List<Autos> listaAutos;

    public AutoAdapter(List<Autos> listaAutos) {
        this.listaAutos = listaAutos;
    }

    @Override
    public int getCount() {
        return listaAutos.size();
    }

    @Override
    public Autos getItem(int position) {
        return listaAutos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return getItem(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view;
        if (convertView==null){
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_lista_autos, parent, false);
        }else {
            view = convertView;
        }

        Autos autos = getItem(position);

        ImageView imgAutoImagen = view.findViewById(R.id.imgAutoImagen);
        TextView txtAutoNombre = view.findViewById(R.id.txtAutoNonbre);
        TextView txtAutoPrecio = view.findViewById(R.id.txtAutoPrecio);
        TextView txtAutoDatos = view.findViewById(R.id.txtAutoDatos);
        TextView txtAutoModelo = view.findViewById(R.id.txtAutoModelo);
        TextView txtAutoCiudad = view.findViewById(R.id.txtAutoCiudad);

        txtAutoNombre.setText(autos.getMarca());
        txtAutoPrecio.setText(String.valueOf("$" + autos.getPrecio()));
        txtAutoDatos.setText(String.valueOf(autos.getKm() + " km - "));
        txtAutoModelo.setText(String.valueOf(autos.getModelo() + " - "));
        txtAutoCiudad.setText(autos.getCiudad());
        imgAutoImagen.setImageResource(autos.getImagen());

        return view;

    }
}
