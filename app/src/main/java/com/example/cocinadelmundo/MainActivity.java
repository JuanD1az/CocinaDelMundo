package com.example.cocinadelmundo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    Button btnMisRecetas,btnSalir,btnRecetasOnline,btnFavoritos;
    TextView textoAviso;

    SensorManager sensorManager;
    Sensor sensor;
    SensorEventListener sensorEventListener;
    int lat = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textoAviso = (TextView) findViewById(R.id.idAviso);

        btnRecetasOnline = (Button) findViewById(R.id.idRecetasOnline);

        btnFavoritos = (Button) findViewById(R.id.Favoritos);

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


        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        sensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        if(sensor==null)
            finish();

        sensorEventListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent sensorEvent) {

                float x = sensorEvent.values[0];
                float y = sensorEvent.values[1];
                float z = sensorEvent.values[2];

                if(x<-5 && lat ==0){
                    textoAviso.setText("No se puede voltear esta pantalla");
                    textoAviso.setTextSize(20);
                    textoAviso.setTextColor(Color.RED);
                    lat++;
                }
                else if(x>-5 && lat==1){
                    lat++;
                    textoAviso.setText("No se puede voltear esta pantalla");
                    textoAviso.setTextSize(20);
                    textoAviso.setTextColor(Color.RED);
                }

                if(lat ==2){
                    lat=0;
                    textoAviso.setText("");
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int i) {

            }
        };
        comenzar();
    }

    private void CambiarActivityMisRecetas(){
        Intent intento = new Intent(this, MisRecetas.class);
        startActivity(intento);
    }


    private void comenzar(){
        sensorManager.registerListener(sensorEventListener,sensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    private  void detener(){
        sensorManager.unregisterListener(sensorEventListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        detener();
    }

    @Override
    protected void onResume() {
        super.onResume();
        comenzar();
    }


}