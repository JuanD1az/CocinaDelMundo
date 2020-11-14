package com.example.cocinadelmundo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.util.ArrayList;

public class MisRecetas extends AppCompatActivity {

    ArrayList<Receta> listDatos;
    RecyclerView recycler;
    String Mandar;

    Button btnAgregarRecetas,btnVolverAtras,btnRefresh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mis_recetas);
        Intent inten = this.getIntent();

        btnAgregarRecetas = (Button) findViewById(R.id.idAgregarReceta);
        btnAgregarRecetas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CambiarActivityAgregarReceta();
            }
        });

        btnVolverAtras = (Button) findViewById(R.id.idVolverAtras);
        btnVolverAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        btnRefresh = (Button) findViewById(R.id.idRefresh);
        btnRefresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recreate();
            }
        });

        recycler = (RecyclerView) findViewById(R.id.recyclerId);
        recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));

        listDatos = new ArrayList<>();

        consultaRecetas();
        Adapter adapter= new Adapter(listDatos);

        adapter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Mandar = listDatos.get(recycler.getChildAdapterPosition(view)).getCodigo();
                CambiarActivityMostrarReceta();
            }
        });

        recycler.setAdapter(adapter);
    }



    private void CambiarActivityAgregarReceta(){
        Intent intento = new Intent(this, AgregarReceta.class);
        startActivity(intento);
    }

    private void CambiarActivityMostrarReceta(){
        Intent intento = new Intent(this, MostrarReceta.class);
        intento.putExtra("codigo",Mandar);
        startActivity(intento);
    }

    private void consultaRecetas(){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        Receta receta= null;
        Cursor cursor = BaseDeDatos.rawQuery("SELECT codigoR, nombre FROM recetas",null);

        while (cursor.moveToNext()){
            receta = new Receta();
            receta.setCodigo(cursor.getString(0));
            receta.setNombre(cursor.getString(1));

            listDatos.add(receta);
        }
        BaseDeDatos.close();
    }
}
