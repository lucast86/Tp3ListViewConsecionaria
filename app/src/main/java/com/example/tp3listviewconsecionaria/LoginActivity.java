package com.example.tp3listviewconsecionaria;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnLoginIngresar;
    private EditText edtLoginEmail, edtLoginPass;
    private Switch swtRecordar;

    private Context ctx;

    private SharedPreferences preferences;
    public static final String PREFS_NAME = "mis_preferencias";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.ctx = this.getApplicationContext();


        getSupportActionBar().setTitle("Control de Acceso");

        findViewsById();

        preferences = getSharedPreferences(PREFS_NAME, MODE_PRIVATE);
        String email = preferences.getString("nombreUsuario", "");
        String pass = preferences.getString("passUsuario", "");

        this.cargarCredencialesPrefs(email, pass);
    }

    private void findViewsById() {
        btnLoginIngresar = findViewById(R.id.btnLoginIngresar);
        edtLoginEmail = findViewById(R.id.edtLoginEmail);
        edtLoginPass = findViewById(R.id.edtLoginPass);
        swtRecordar = findViewById(R.id.swtRecordar);

        btnLoginIngresar.setOnClickListener((View.OnClickListener) this);
    }

    private void cargarCredencialesPrefs(String email, String pass) {
        edtLoginEmail.setText(email);
        edtLoginPass.setText(pass);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLoginIngresar:
                // si esta activo recordar guardamos credenciales, sino las limpiamos
                if (validarUsuario()) {
                    if (swtRecordar.isChecked()) {
                        this.grabarCredencialesPrefs(edtLoginEmail.getText().toString(), edtLoginPass.getText().toString());
                        Toast.makeText(ctx, "Valores Grabados OK", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);

                        builder.setIcon(R.drawable.img_login);
                        builder.setTitle("Control de Acceso");
                        builder.setMessage("¿Desea continuar si grabar el Usuario?");
                        builder.setPositiveButton("Aceptar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                this.limpiarCredencialesPrefs();
                                Toast.makeText(ctx, "No se Grabo Usuario y contraseña ", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                startActivity(intent);
                            }

                            private void limpiarCredencialesPrefs() {
                                SharedPreferences.Editor editor = preferences.edit();
                                editor.clear();
                                editor.commit();
                                edtLoginEmail.setText("");
                                edtLoginPass.setText("");
                            }
                        });
                        builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(ctx, "ACTIVAR RECORDARME", Toast.LENGTH_SHORT).show();
                            }
                        });
                        AlertDialog alertDialog = builder.create();
                        alertDialog.show();
                    }
                }
        }
    }

    private void grabarCredencialesPrefs(String email, String pass) {
        SharedPreferences.Editor editor = preferences.edit();
        // OJO: encriptar password antes de almacenar
        // String passs = Utils.convertirSHA256(pass);
        editor.putString("nombreUsuario", email);
        editor.putString("passUsuario", pass);
        editor.commit();
        //notificacion Mediante Snackbar -> requiere Support Design Library
        // Snackbar.make(findViewById(R.id.main_layout),"Valores Grabados OK",Snackbar.LENGTH_SHORT).show();

    }

    private void limpiarCredencialesPrefs() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.clear();
        editor.commit();
        edtLoginEmail.setText("");
        edtLoginPass.setText("");
        //notificacion Mediante Snackbar -> requiere Support Design Library
        // Snackbar.make(findViewById(R.id.main_layout),"Valores Borrados OK",Snackbar.LENGTH_SHORT).show();
    }

    private boolean validarUsuario() {
        // TODO: completar validaciones necesarias pre-grabación del empleado
        boolean valido = true;
        // campo Marca requerido
        if (edtLoginEmail.getText().toString().isEmpty()) {
            valido = false;
            edtLoginEmail.setError("Debe completar este campo");
        }
        // campo Modelo requerido
        if (edtLoginPass.getText().toString().isEmpty()) {
            valido = false;
            edtLoginPass.setError("Debe completar este campo");
        }
        return valido;
    }
}