package com.app.cocinadelmundo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class FireBase extends AppCompatActivity {

    EditText txtCodigo, txtNom, txtIngredientes, txtPais, txtPasos;
    Button btnIn, btnElim, btnBusc, btnAct;

    FirebaseDatabase FBData;
    DatabaseReference DBReference,DBMostrar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fire_base);
        Intent inten = this.getIntent();

        txtCodigo = (EditText) findViewById(R.id.txtCodigo);
        txtNom = (EditText) findViewById(R.id.txtNombre);
        txtPais = (EditText) findViewById(R.id.txtPais);
        txtIngredientes = (EditText) findViewById(R.id.txtIngredientes);
        txtPasos = (EditText) findViewById(R.id.txtPasos);

        btnIn = (Button) findViewById(R.id.button_ING);
        btnIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String CODIGO = txtCodigo.getText().toString().trim().toUpperCase();
                String NOMBRE = txtNom.getText().toString();
                String INGREDIENTES = txtIngredientes.getText().toString();
                String PASOS = txtPasos.getText().toString();
                String PAIS = txtPais.getText().toString().trim().toUpperCase();
                if(CODIGO.equals("")||NOMBRE.equals("")||INGREDIENTES.equals("")||PASOS.equals("")||PAIS.equals(""))
                {
                    validar();
                }
                else {
                    RecetasOnline P = new RecetasOnline();
                    P.setCodigo(CODIGO);
                    P.setNombre(NOMBRE);
                    P.setIngredientes(INGREDIENTES);
                    P.setPasos(PASOS);
                    P.setPais(PAIS);
                    DBReference.child(P.getPais()).child(P.getCodigo()).setValue(P);
                    Toast.makeText(FireBase.this, "GUARDANDO !!!!!", Toast.LENGTH_LONG).show();
                    limpiar();
                }
            }
        });

        btnAct = (Button) findViewById(R.id.button_UPD);
        btnAct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String CODIGO = txtCodigo.getText().toString().trim().toUpperCase();
                String NOMBRE = txtNom.getText().toString();
                String INGREDIENTES = txtIngredientes.getText().toString();
                String PASOS = txtPasos.getText().toString();
                String PAIS = txtPais.getText().toString().trim().toUpperCase();
                if(CODIGO.equals("")||NOMBRE.equals("")||INGREDIENTES.equals("")||PASOS.equals("")||PAIS.equals(""))
                {
                    validar();
                }
                else {
                    RecetasOnline P = new RecetasOnline();
                    P.setCodigo(CODIGO);
                    P.setNombre(NOMBRE);
                    P.setIngredientes(INGREDIENTES);
                    P.setPasos(PASOS);
                    P.setPais(PAIS);
                    DBReference.child(P.getPais()).child(P.getCodigo()).setValue(P);
                    Toast.makeText(FireBase.this, "GUARDANDO !!!!!", Toast.LENGTH_LONG).show();
                    limpiar();
                }
            }
        });


        btnElim = (Button) findViewById(R.id.button_ELIM);
        btnElim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Codigo = txtCodigo.getText().toString().trim().toUpperCase();
                String Pais = txtPais.getText().toString().trim().toUpperCase();
                if(Codigo.equals("")|| Pais.equals("") )
                {
                    validar();
                }
                else {
                    DBReference.child(Pais).child(Codigo).removeValue();
                    Toast.makeText(FireBase.this, "ELIMINANDO !!!!!", Toast.LENGTH_LONG).show();
                    limpiar();
                }
            }
        });

        btnBusc = (Button) findViewById(R.id.button_Cons);
        btnBusc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String Codigo = txtCodigo.getText().toString().trim().toUpperCase();
                String Pais = txtPais.getText().toString().trim().toUpperCase();
                if (Codigo.equals("") || Pais.equals("")) {
                    validar();
                } else {
                    final int[] cont = {0};
                    DatabaseReference bbdd = FirebaseDatabase.getInstance().getReference(Pais);

                    Query q = bbdd.orderByChild("codigo").equalTo(Codigo);

                    q.addChildEventListener(new ChildEventListener() {
                        @Override
                        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String previousChildName) {

                            for (DataSnapshot datasnapshot : dataSnapshot.getChildren()) {
                                cont[0]++;
                                System.out.println(dataSnapshot.getValue());

                                RecetasOnline miP = dataSnapshot.getValue(RecetasOnline.class);
                                txtNom.setText(miP.getNombre().toString());
                                txtIngredientes.setText(miP.getIngredientes().toString());
                                txtPasos.setText(miP.getPasos().toString());
                                Toast.makeText(FireBase.this, "ENCONTRADO !!! ", Toast.LENGTH_LONG).show();
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

                    if (cont[0] == 0) {
                        Toast.makeText(FireBase.this, "NO EXISTE ESA PERSONA!!! ", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });

        iniciar_firebase();

    } // FIN ONCREATE

    private void iniciar_firebase() {
        FirebaseApp.initializeApp(this);
        this.FBData = FirebaseDatabase.getInstance();
        this.DBReference = this.FBData.getReference();
    }

    private void limpiar() {
        this.txtCodigo.setText("");
        this.txtNom.setText("");
        this.txtPais.setText("");
        this.txtPasos.setText("");
        this.txtIngredientes.setText("");
    }

    private void validar() {
        if(txtCodigo.getText().toString().equals(""))
        {
            txtCodigo.setError("Escriba Codigo");
        }
        if(txtNom.getText().toString().equals(""))
        {
            txtNom.setError("Escriba Nombre");
        }
        if(txtPais.getText().toString().equals(""))
        {
            txtPais.setError("Escriba Pais");
        }
        if(txtIngredientes.getText().toString().equals(""))
        {
            txtIngredientes.setError("Escriba Ingredientes");
        }
        if(txtPasos.getText().toString().equals(""))
        {
            txtPasos.setError("Escriba Pasis");
        }
    }
}

