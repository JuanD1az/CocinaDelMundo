package com.example.cocinadelmundo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PaisesTest extends AppCompatActivity {

    Button btnPaisAdd, btnPaisBuscar;
    private EditText et_pais,et_nombre,et_ingredientes,et_pasos,et_codigo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paises_test);
        Intent inten = this.getIntent();

        btnPaisAdd = (Button) findViewById(R.id.idAgregarPais);
        btnPaisAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RegistrarPais();
            }
        });

        btnPaisBuscar = (Button) findViewById(R.id.idBuscarPais);
        btnPaisBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BuscarPais();
            }
        });


        et_pais = (EditText) findViewById(R.id.idPaisT);
        et_nombre = (EditText) findViewById(R.id.idNombreT);
        et_ingredientes = (EditText) findViewById(R.id.idIngredienteT);
        et_pasos = (EditText) findViewById(R.id.idPasosT);
        et_codigo = (EditText) findViewById(R.id.idCodigoT);
    }

    public void RegistrarPais() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String pais = et_pais.getText().toString().trim().toUpperCase();
        String nombre = et_nombre.getText().toString().trim().toUpperCase();
        String ingredientes = et_ingredientes.getText().toString().trim().toUpperCase();
        String pasos = et_pasos.getText().toString().trim().toUpperCase();
        String codigo = et_codigo.getText().toString().trim().toUpperCase();



        if(!pais.isEmpty() && !nombre.isEmpty() && !ingredientes.isEmpty() && !pasos.isEmpty() && !codigo.isEmpty()){
            ContentValues registro = new ContentValues();
            registro.put("codigoRO", codigo);
            registro.put("nombre", nombre);
            registro.put("ingredientes", ingredientes);
            registro.put("pasos", pasos);
            registro.put("pais", pais);

            BaseDeDatos.insert("recetasOnline", null, registro);
            BaseDeDatos.close();

            et_pais.setText("");
            et_codigo.setText("");
            et_nombre.setText("");
            et_ingredientes.setText("");
            et_pasos.setText("");

            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Error al registrarlo", Toast.LENGTH_SHORT).show();
        }
    }

    public void BuscarPais() {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        String codigo = et_codigo.getText().toString();

        if(!codigo.isEmpty()){
            Cursor fila = BaseDeDatos.rawQuery
                    ("select nombre, ingredientes, pasos, pais from recetasOnline where codigoRO =" + codigo, null);
            if(fila.moveToFirst()) {
                et_nombre.setText(fila.getString(0));
                et_ingredientes.setText(fila.getString(1));
                et_pasos.setText(fila.getString(2));
                et_pais.setText(fila.getString(3));
                BaseDeDatos.close();
                Toast.makeText(this, "Test:", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            }
        }else{
            Toast.makeText(this, "Debes ingresar el pais", Toast.LENGTH_SHORT).show();
        }
    }


}