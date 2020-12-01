package com.app.cocinadelmundo;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AgregarReceta extends AppCompatActivity {

    Button btnVolverAtras, btnGuardar;
    private EditText et_nombre,et_ingredientes,et_pasos,et_codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plantilla_agregar);
        Intent inten = this.getIntent();

        btnVolverAtras = (Button) findViewById(R.id.idVolverAtras);
        btnVolverAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });


        btnGuardar = (Button) findViewById(R.id.idGuardar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Registrar(view);
            }
        });

        et_nombre = (EditText) findViewById(R.id.idNombre);
        et_ingredientes = (EditText) findViewById(R.id.idIngredientes);
        et_pasos = (EditText) findViewById(R.id.idPasos);
        et_codigo = (EditText) findViewById(R.id.idCodigo);
    }

    public void Registrar(View view) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String nombre = et_nombre.getText().toString();
        String ingredientes = et_ingredientes.getText().toString();
        String pasos = et_pasos.getText().toString();
        String codigo = et_codigo.getText().toString().trim().toUpperCase();

        if(!nombre.isEmpty() && !ingredientes.isEmpty() && !pasos.isEmpty() && !codigo.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("codigoR", codigo);
            registro.put("nombre", nombre);
            registro.put("ingredientes", ingredientes);
            registro.put("pasos", pasos);

            Cursor fila = BaseDeDatos.rawQuery
                    ("select * from recetas where codigoR=" + codigo, null);
            if(fila.moveToFirst()){
                Toast.makeText(this, "El codigo ya esta en uso", Toast.LENGTH_SHORT).show();
            }else{
                BaseDeDatos.insert("recetas", null, registro);
                BaseDeDatos.close();

                et_codigo.setText("");
                et_nombre.setText("");
                et_ingredientes.setText("");
                et_pasos.setText("");

                Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }
}