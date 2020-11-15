package com.example.cocinadelmundo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MostrarRecetaOnline extends AppCompatActivity {
    Button btnVolverAtras;
    private EditText et_nombre,et_ingredientes,et_pasos,et_codigo;
    String C;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plantilla_mostraronline);
        Intent inten = this.getIntent();
        C = inten.getStringExtra("codigo1".toString());

        et_codigo = (EditText) findViewById(R.id.idCodigo);
        et_codigo.setText(C);
        et_nombre = (EditText) findViewById(R.id.idNombre);
        et_ingredientes = (EditText) findViewById(R.id.idIngredientes);
        et_pasos = (EditText) findViewById(R.id.idPasos);

        Buscar();

        btnVolverAtras = (Button) findViewById(R.id.idVolverAtras);
        btnVolverAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    public void Buscar() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String codigo = et_codigo.getText().toString();

        if(!codigo.isEmpty()){
            Cursor fila = BaseDeDatos.rawQuery
                    ("select nombre, ingredientes, pasos from recetasOnline where codigoRO =" + codigo, null);

            if(fila.moveToFirst()){
                et_nombre.setText(fila.getString(0));
                et_ingredientes.setText(fila.getString(1));
                et_pasos.setText(fila.getString(2));
                BaseDeDatos.close();
            }else{
                Toast.makeText(this, "La receta no existe", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Debes ingresar el codigo", Toast.LENGTH_SHORT).show();
        }
    }


}
