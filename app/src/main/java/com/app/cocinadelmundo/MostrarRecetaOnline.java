package com.app.cocinadelmundo;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class MostrarRecetaOnline extends AppCompatActivity {
    Button btnVolverAtras;
    private EditText et_nombre, et_ingredientes, et_pasos, et_codigo;
    String C,P;

    FirebaseDatabase FBData;
    DatabaseReference DBReference,DBMostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plantilla_mostraronline1);
        Intent inten = this.getIntent();
        this.C = inten.getStringExtra("codigo1".toString());
        this.P = inten.getStringExtra("pais1".toString());

        et_codigo = (EditText) findViewById(R.id.idCodigo);
        et_codigo.setText(C);
        et_nombre = (EditText) findViewById(R.id.idNombre);
        et_ingredientes = (EditText) findViewById(R.id.idIngredientes);
        et_pasos = (EditText) findViewById(R.id.idPasos);

        iniciar_firebase();
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
        final int[] cont = {0};
        DatabaseReference bbdd = FirebaseDatabase.getInstance().getReference(P);

        Query q=bbdd.orderByChild("codigo").equalTo(C);

        q.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {

                for(DataSnapshot datasnapshot: dataSnapshot.getChildren()){
                    cont[0]++;

                    System.out.println(dataSnapshot.getValue());

                    RecetasOnline receta = dataSnapshot.getValue(RecetasOnline.class);
                    et_nombre.setText(receta.getNombre().toString());
                    et_pasos.setText(receta.getPasos().toString());
                    et_ingredientes.setText(receta.getIngredientes().toString());
                    break;
                }
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });








        /*AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
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
        }*/
    }

    private void iniciar_firebase() {
        FirebaseApp.initializeApp(this);
        this.FBData = FirebaseDatabase.getInstance();
        this.DBReference = this.FBData.getReference();
    }

}