package com.example.tp3listviewconsecionaria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tp3listviewconsecionaria.models.Autos;

public class DetalleActivity extends AppCompatActivity {

    private TextView txtAutoSegPantallaModelo, txtAutoSegPantallaKm, txtAutoSegPantallaMarca;
    private TextView txtAutoSegPantallaPercio, txtAutoSegPantallaDescipcion;
    private ImageView imgAutoSegPantalla;

    private DbHelper dbHelper;
    private SQLiteDatabase db;
    private Context ctx;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        this.ctx = this.getApplicationContext();

        getSupportActionBar().setTitle("DETALLES");

        findViews();

        dbHelper = new DbHelper(this.ctx);
        db = dbHelper.getWritableDatabase();

        Intent intent = getIntent();

        /*Bundle extras = intent.getExtras();

        txtAutoSegPantallaModelo.setText(String.valueOf(extras.getInt("E_MODELO")) + "      - ");
        txtAutoSegPantallaKm.setText( String.valueOf(extras.getFloat("E_KM")) + " km");
        txtAutoSegPantallaMarca.setText(extras.getString("E_MARCA"));
        txtAutoSegPantallaPercio.setText("$ " + String.valueOf(extras.getFloat("E_PRECIO")));
        txtAutoSegPantallaDescipcion.setText(extras.getString("E_DESCRIPCION"));
        imgAutoSegPantalla.setImageResource(extras.getInt("E_IMAGEN"));
        */
        Intent i = getIntent();

        int id = i.getIntExtra("E_ID", 0);

        if (id != 0) {
            Toast.makeText(ctx, "seleccionó: " + id, Toast.LENGTH_SHORT).show();
            cargarItemSqlite(id);
        }
    }

    private void findViews(){
        txtAutoSegPantallaModelo = findViewById(R.id.txtAutoSegPantallaModelo);
        txtAutoSegPantallaKm = findViewById(R.id.txtAutoSegPantallaKm);
        txtAutoSegPantallaMarca = findViewById(R.id.txtAutoSegPantallaMarca);
        txtAutoSegPantallaPercio = findViewById(R.id.txtAutoSegPantallaPercio);
        txtAutoSegPantallaDescipcion = findViewById(R.id.txtAutoSegPantallaDescipcion);
        imgAutoSegPantalla = findViewById(R.id.imgAutoSegPantalla);
    }

    private void cargarItemSqlite(int id) {
        // obtenemos datos de SQLite
        //String consulta = "SELECT * FROM Empleados WHERE idempleado="+ ID;

        //seleccionamos todos los registros

        Cursor cursor = db.rawQuery("SELECT * FROM autos  WHERE id=? ", new String[]{String.valueOf(id)});

        //nos posicionamos al inicio del curso
        if(cursor!=null && cursor.moveToLast()) {

            //iteramos todos los registros del cursor y llenamos array con registros
            txtAutoSegPantallaMarca.setText(cursor.getString(cursor.getColumnIndex("marca")));
            txtAutoSegPantallaModelo.setText(cursor.getString(cursor.getColumnIndex("modelo")));
            txtAutoSegPantallaPercio.setText("$" + cursor.getString(cursor.getColumnIndex("precio")));
            txtAutoSegPantallaKm.setText(cursor.getString(cursor.getColumnIndex("km")) + " km");
            txtAutoSegPantallaDescipcion.setText(cursor.getString(cursor.getColumnIndex("descipcion")));
        }else{
            Toast.makeText(ctx, "No hay registros", Toast.LENGTH_SHORT).show();
        }
        db.close();

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