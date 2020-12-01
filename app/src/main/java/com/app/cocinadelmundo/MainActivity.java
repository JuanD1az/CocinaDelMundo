package com.app.cocinadelmundo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

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
        //Inicializar();

        textoAviso = (TextView) findViewById(R.id.idAviso);

        btnRecetasOnline = (Button) findViewById(R.id.idRecetasOnline);
        btnRecetasOnline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CambiarActivityRecetasOnline();
            }
        });

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

    private void CambiarActivityRecetasOnline(){
        Intent intento = new Intent(this, MapsActivity.class);
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

    //Poblar tabla recetasOnline
    /*
    private void Inicializar(){
        if(cantidadRegistros()==0){
            String[] texto = leerArchivo();
            AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                    "administracion", null, 1);
            SQLiteDatabase db = admin.getWritableDatabase();
            db.beginTransaction();
            for(int i=0;i<texto.length;i++){
                String[] linea = texto[i].split(";");
                ContentValues contentValues = new ContentValues();
                contentValues.put("codigoRO",linea[0]);
                contentValues.put("nombre",linea[1]);
                contentValues.put("ingredientes",linea[2]);
                contentValues.put("pasos",linea[3]);
                contentValues.put("pais",linea[4]);
                db.insert("recetasOnline",null,contentValues);
            }
            Toast.makeText(this,"Bienvenido!!!",Toast.LENGTH_SHORT).show();
            db.setTransactionSuccessful();
            db.endTransaction();
        }
    }

    private long cantidadRegistros(){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase db = admin.getReadableDatabase();
        long cn = DatabaseUtils.queryNumEntries(db,"recetasOnline");
        db.close();
        return cn;
    }

    private String[] leerArchivo(){
        InputStream inputStream = getResources().openRawResource(R.raw.registros);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try{
            int i = inputStream.read();
            while (i != -1){
                byteArrayOutputStream.write(i);
                i = inputStream.read();
            }
            inputStream.close();
        }catch(IOException e){
            e.printStackTrace();
        }
        return byteArrayOutputStream.toString().split("\n");
    }
    */





}