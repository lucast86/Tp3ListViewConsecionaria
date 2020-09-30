package com.example.tp3listviewconsecionaria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

public class DetalleActivity extends AppCompatActivity {

    private TextView txtAutoSegPantallaModelo, txtAutoSegPantallaKm, txtAutoSegPantallaMarca;
    private TextView txtAutoSegPantallaPercio, txtAutoSegPantallaDescipcion;
    private ImageView imgAutoSegPantalla;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        getSupportActionBar().setTitle("DETALLES");

        findViews();

        Intent intent = getIntent();

        Bundle extras = intent.getExtras();

        txtAutoSegPantallaModelo.setText(String.valueOf(extras.getInt("E_MODELO")) + "      - ");
        txtAutoSegPantallaKm.setText( String.valueOf(extras.getFloat("E_KM")) + " km");
        txtAutoSegPantallaMarca.setText(extras.getString("E_MARCA"));
        txtAutoSegPantallaPercio.setText("$ " + String.valueOf(extras.getFloat("E_PRECIO")));
        txtAutoSegPantallaDescipcion.setText(extras.getString("E_DESCRIPCION"));
        imgAutoSegPantalla.setImageResource(extras.getInt("E_IMAGEN"));

    }

    private void findViews(){
        txtAutoSegPantallaModelo = findViewById(R.id.txtAutoSegPantallaModelo);
        txtAutoSegPantallaKm = findViewById(R.id.txtAutoSegPantallaKm);
        txtAutoSegPantallaMarca = findViewById(R.id.txtAutoSegPantallaMarca);
        txtAutoSegPantallaPercio = findViewById(R.id.txtAutoSegPantallaPercio);
        txtAutoSegPantallaDescipcion = findViewById(R.id.txtAutoSegPantallaDescipcion);
        imgAutoSegPantalla = findViewById(R.id.imgAutoSegPantalla);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_detalles, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.mnuDetallesRetornar:
                finish();
        }

        return super.onOptionsItemSelected(item);
    }
}