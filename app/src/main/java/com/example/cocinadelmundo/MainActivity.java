package com.example.cocinadelmundo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button btnMisRecetas,btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnMisRecetas = (Button) findViewById(R.id.idMisRecetas);
        btnMisRecetas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CambiarActivityMisRecetas();
            }
        });


        btnSalir = (Button) findViewById(R.id.idSalir);
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finishAffinity();
            }
        });

    }


    private void CambiarActivityMisRecetas(){
        Intent intento = new Intent(this, MisRecetas.class);
        startActivity(intento);
    }
}