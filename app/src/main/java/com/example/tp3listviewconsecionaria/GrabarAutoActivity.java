    package com.example.tp3listviewconsecionaria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

    public class GrabarAutoActivity extends AppCompatActivity implements View.OnClickListener {

        private EditText edtGrabarMarcaAuto, edtGrabarModeloAuto, edtGrabarPrecioAuto,
                edtGrabarKilometrosAuto, edtGrabarDescripcionAuto;
        private Switch swtGrabarFavorito, swtGrabarHabilitado;
        private Button btnGrabarCancelarAuto;
        private Button btnGrabarAuto;

        private DbHelper dbHelper;
        private SQLiteDatabase db;
        private Context ctx;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_grabar_auto);

            this.ctx = this.getApplicationContext();

            Intent i = getIntent();

            int id = i.getIntExtra("ID", 0);

            getSupportActionBar().setTitle("Cargar Auto Nuevo");

            findViewsById();

            //abrimos db en modo escritura
            dbHelper = new DbHelper(this.ctx);
            db = dbHelper.getWritableDatabase();

            if (id != 0) {
                Toast.makeText(ctx, "seleccionó: " + id, Toast.LENGTH_SHORT).show();
                cargarItemSqlite(id);
            }

        }
        // referencias a los controles en la vista
        private void findViewsById() {
            // cuadros de texto
            edtGrabarMarcaAuto = findViewById(R.id.edtGrabarMarcaAuto);
            edtGrabarModeloAuto = findViewById(R.id.edtGrabarModeloAuto);
            edtGrabarPrecioAuto = findViewById(R.id.edtGrabarPrecioAuto);
            edtGrabarKilometrosAuto = findViewById(R.id.edtGrabarKilometrosAuto);
            edtGrabarDescripcionAuto = findViewById(R.id.edtGrabarDescripcionAuto);

            // controles switch
            swtGrabarFavorito = findViewById(R.id.swtGrabarFavorito);
            swtGrabarHabilitado = findViewById(R.id.swtGrabarHabilitado);

            // botones
            btnGrabarCancelarAuto = findViewById(R.id.btnGrabarCancelarAuto);
            btnGrabarAuto = findViewById(R.id.btnGrabarAuto);

            // clic listeners
            btnGrabarCancelarAuto.setOnClickListener(this);
            btnGrabarAuto.setOnClickListener(this);
        }

        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btnGrabarCancelarAuto:
                    Intent intent = new Intent(GrabarAutoActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case R.id.btnGrabarAuto:
                    agregarItemSqlite();
                    Intent intents = new Intent(GrabarAutoActivity.this, MainActivity.class);
                    startActivity(intents);
                    finish();
                    break;
            }
        }

        private void cargarItemSqlite(int id) {
            // obtenemos datos de SQLite
            //String consulta = "SELECT * FROM Empleados WHERE idempleado="+ ID;

            //seleccionamos todos los registros

            Cursor cursor = db.rawQuery("SELECT * FROM autos  WHERE id=?", new String[]{String.valueOf(id)});

            //nos posicionamos al inicio del curso
            if(cursor!=null && cursor.moveToLast()) {

                //iteramos todos los registros del cursor y llenamos array con registros
                edtGrabarMarcaAuto.setText(cursor.getString(cursor.getColumnIndex("marca")));
                edtGrabarModeloAuto.setText(cursor.getString(cursor.getColumnIndex("modelo")));
                edtGrabarPrecioAuto.setText(cursor.getString(cursor.getColumnIndex("precio")));
                edtGrabarKilometrosAuto.setText(cursor.getString(cursor.getColumnIndex("km")));
                edtGrabarDescripcionAuto.setText(cursor.getString(cursor.getColumnIndex("descipcion")));
            }else{
               // Toast.makeText(ctx, "No hay registros", Toast.LENGTH_SHORT).show();
            }

            db.close();

        }

        private void agregarItemSqlite(){

            // verificamos que los valores sean validos
            if(validarItems()){

                ContentValues nuevoRegistro = new ContentValues();
                nuevoRegistro.put("marca", edtGrabarMarcaAuto.getText().toString());
                nuevoRegistro.put("modelo",edtGrabarModeloAuto.getText().toString());
                nuevoRegistro.put("precio",edtGrabarPrecioAuto.getText().toString());
                nuevoRegistro.put("km",edtGrabarKilometrosAuto.getText().toString());
                nuevoRegistro.put("descipcion",edtGrabarDescripcionAuto.getText().toString());
                db.insert("autos", null, nuevoRegistro);

                Toast.makeText(ctx, "Registro Grabado OK", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(ctx, "Verifique datos inválidos", Toast.LENGTH_SHORT).show();
            }
        }

        private boolean validarItems(){
            // TODO: completar validaciones necesarias pre-grabación del empleado
            boolean valido=true;
            // campo Marca requerido
            if(edtGrabarMarcaAuto.getText().toString().isEmpty()){
                valido=false;
                edtGrabarMarcaAuto.setError("Debe completar este campo");
            }
            // campo Modelo requerido
            if(edtGrabarModeloAuto.getText().toString().isEmpty()){
                valido=false;
                edtGrabarModeloAuto.setError("Debe completar este campo");
            }
            // campo Precio requerido
            if(edtGrabarPrecioAuto.getText().toString().isEmpty()){
                valido=false;
                edtGrabarPrecioAuto.setError("Debe completar este campo");
            }
            // campo Kilometros requerido
            if(edtGrabarKilometrosAuto.getText().toString().isEmpty()){
                valido=false;
                edtGrabarKilometrosAuto.setError("Debe completar este campo");
            }
            return valido;
        }

        @Override
        protected void onDestroy() {
            super.onDestroy();
            db.close();
        }
    }

