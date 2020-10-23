    package com.example.tp3listviewconsecionaria;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

    public class GrabarAutoActivity extends AppCompatActivity implements View.OnClickListener{

        private Button btnGrabarCancelarAuto;
        private Button btnGrabarAuto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grabar_auto);

        getSupportActionBar().setTitle("Cargar Auto Nuevo");

        findViewsById();
    }

        private void findViewsById() {
            btnGrabarCancelarAuto = findViewById(R.id.btnGrabarCancelarAuto);
            btnGrabarAuto  = findViewById(R.id.btnGrabarAuto);

            btnGrabarCancelarAuto.setOnClickListener((View.OnClickListener) this);
        }

        public void onClick(View v) {
            switch (v.getId()){
                case R.id.btnGrabarCancelarAuto:
                    Intent intent = new Intent(GrabarAutoActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    break;
                case R.id.btnGrabarAuto:
                    //agregarItemSqlite();
                    break;
            }
        }

}