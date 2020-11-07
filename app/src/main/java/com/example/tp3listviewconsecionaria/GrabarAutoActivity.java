    package com.example.tp3listviewconsecionaria;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import com.example.tp3listviewconsecionaria.models.Autos;

import java.util.List;

    public class GrabarAutoActivity extends AppCompatActivity implements View.OnClickListener {

        private EditText edtGrabarMarcaAuto, edtGrabarModeloAuto, edtGrabarPrecioAuto,
                edtGrabarKilometrosAuto, edtGrabarDescripcionAuto;
        private Switch swtGrabarFavorito, swtGrabarHabilitado;
        private Button btnGrabarCancelarAuto;
        private Button btnGrabarAuto, btnGrabarFoto;
        private ImageView imgGrabarFoto;

        private DbHelper dbHelper;
        private SQLiteDatabase db;
        private Context ctx;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_grabar_auto);

            this.ctx = this.getApplicationContext();

            Intent i = getIntent();

            getSupportActionBar().setTitle("Cargar Auto Nuevo");

            findViewsById();

            //abrimos db en modo escritura
            dbHelper = new DbHelper(this.ctx);
            db = dbHelper.getWritableDatabase();

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
            btnGrabarFoto = findViewById(R.id.btnGrabarFoto);

            //ImageView
            imgGrabarFoto = findViewById(R.id.imgGrabarFoto);

            // clic listeners
            btnGrabarCancelarAuto.setOnClickListener(this);
            btnGrabarAuto.setOnClickListener(this);
            btnGrabarFoto.setOnClickListener(this);
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
                case R.id.btnGrabarFoto:
                    cargarImagen();
                    //mostrarDialogoOpciones();
                    break;
            }
        }

        private void cargarImagen() {
            Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            intent.setType("image/");
            startActivityForResult(intent.createChooser(intent, "Seleccione la Aplicación"), 10);
        }

        @Override
        protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
            super.onActivityResult(requestCode, resultCode, data);
            if (requestCode == RESULT_OK) {
                Uri path = data.getData();
                imgGrabarFoto.setImageURI(path);
            }
        }

        /* private void   mostrarDialogoOpciones(){
            final charSequence[] opciones={ "Tomar Foto",  "Elegir de la Galeria", "Cancelar"};
        }*/

        private void agregarItemSqlite() {

            // verificamos que los valores sean validos
            if (validarItems()) {

                ContentValues nuevoRegistro = new ContentValues();
                nuevoRegistro.put("marca", edtGrabarMarcaAuto.getText().toString());
                nuevoRegistro.put("modelo", edtGrabarModeloAuto.getText().toString());
                nuevoRegistro.put("precio", edtGrabarPrecioAuto.getText().toString());
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

