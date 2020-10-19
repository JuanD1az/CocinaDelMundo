package com.example.cocinadelmundo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MisRecetas extends AppCompatActivity {

    Button btnAgregarRecetas,btnVolverAtras;

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
    }

    private void CambiarActivityAgregarReceta(){
        Intent intento = new Intent(this, AgregarReceta.class);
        startActivity(intento);
    }

}
