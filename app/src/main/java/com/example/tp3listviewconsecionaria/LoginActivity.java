package com.example.tp3listviewconsecionaria;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnLoginIngresar;
    private EditText edtLoginEmail, edtLoginPass;
    private Switch swtRecordar;

    private SharedPreferences preferences;
    public static final String PREFS_NAME = "mis_preferencias";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        getSupportActionBar().setTitle("Control de Acceso");

        findViewsById();

        preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String email = preferences.getString("nombreUsuario", "");
        String password = preferences.getString("passwordUsuario", "");

        this.cargarCredencialesPrefs(password, email);
    }

    private void findViewsById() {
        btnLoginIngresar = findViewById(R.id.btnLoginIngresar);
        edtLoginEmail = findViewById(R.id.edtLoginEmail);
        edtLoginPass = findViewById(R.id.edtLoginPass);
        swtRecordar = findViewById(R.id.swtRecordar);

        btnLoginIngresar.setOnClickListener((View.OnClickListener) this);
    }

    private void cargarCredencialesPrefs(String password, String email) {
        edtLoginPass.setText(password);
        edtLoginEmail.setText(email);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLoginIngresar:

                // si esta activo recordar guardamos credenciales, sino las limpiamos
                if (swtRecordar.isChecked()) {
                    this.grabarCredencialesPrefs(edtLoginPass.getText().toString(),edtLoginEmail.getText().toString());
                } else {
                    this.limpiarCredencialesPrefs();
                }

                Intent intent = new Intent(LoginActivity.this,MainActivity.class);
                startActivity(intent);

        }
    }

    private void grabarCredencialesPrefs(String password,String email) {
        SharedPreferences.Editor editor = preferences.edit();
        // OJO: encriptar password antes de almacenar
       String p = Utils.convertirSHA256(password);
        editor.putString("passwordUsuario", p);
        editor.putString("nombreUsuario", email);
        editor.commit();
        //notificacion Mediante Snackbar -> requiere Support Design Library
        // Snackbar.make(findViewById(R.id.main_layout),"Valores Grabados OK",Snackbar.LENGTH_SHORT).show();

    }

    private void limpiarCredencialesPrefs(){
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
        edtLoginPass.setText("");
        edtLoginEmail.setText("");
        //notificacion Mediante Snackbar -> requiere Support Design Library
        // Snackbar.make(findViewById(R.id.main_layout),"Valores Borrados OK",Snackbar.LENGTH_SHORT).show();
    }
}