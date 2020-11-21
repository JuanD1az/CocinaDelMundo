package com.app.cocinadelmundo;

import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, AdapterView.OnItemSelectedListener {

    private GoogleMap mMap;
    Button btnVolverAtras;
    Spinner dropdown;
    static final String[] paths = {"Chile","Argentina","Peru"};

    ArrayList<RecetasOnline> listDatos;
    RecyclerView recycler;
    String paises;
    String Mandar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        Intent inten = this.getIntent();


        recycler = (RecyclerView) findViewById(R.id.recycler2id);
        recycler.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));



        btnVolverAtras = (Button) findViewById(R.id.idVolverAtras);
        btnVolverAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        dropdown = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, paths);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(this);





        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        int estado = GooglePlayServicesUtil.isGooglePlayServicesAvailable(getApplicationContext());
        if(estado == ConnectionResult.SUCCESS) {
            SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                    .findFragmentById(R.id.map);
            mapFragment.getMapAsync(this);
        }
        else{
            Dialog mens = GooglePlayServicesUtil.getErrorDialog(estado, (Activity) getApplicationContext(), 10);
            mens.show();
        }
    }

                               /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
                               @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        //Tipo de mapa
        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        //Utilidades del map
        UiSettings USet = mMap.getUiSettings();
        USet.setZoomControlsEnabled(true);


        // Add a marker in Sydney and move the camera
        LatLng chile = new LatLng(-30.604494, -71.2047422);
        float zoom = 3;
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(chile,zoom));
    }


    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
        float zoom = 3;
        listDatos=new ArrayList<>();
        switch (position){
            case 0:
                mMap.clear();
                LatLng chile = new LatLng(-33.4718999, -70.9100243);
                mMap.addMarker(new MarkerOptions().position(chile).title("Marcador de Chile").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(chile,zoom));
                paises = "CHILE";
                llenarLista();
                break;

            case 1:
                mMap.clear();
                LatLng Argentina = new LatLng(-35.539666, -65.3581207);
                mMap.addMarker(new MarkerOptions().position(Argentina).title("Marcador de Argentina").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Argentina,zoom));
                paises = "ARGENTINA";
                llenarLista();
                break;

            case 2:
                mMap.clear();
                LatLng Peru = new LatLng(-12.5063601, -75.7967129);
                mMap.addMarker(new MarkerOptions().position(Peru).title("Marcador de Peru").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(Peru,zoom));
                paises = "PERU";
                llenarLista();
                break;
        }
        Adapter2 adapter2 = new Adapter2(listDatos);

        adapter2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Mandar = listDatos.get(recycler.getChildAdapterPosition(view)).getCodigo();
                CambiarActivityMostrarRecetaOnline();
            }
        });
        recycler.setAdapter(adapter2);
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private void llenarLista(){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase BaseDeDatos = admin.getWritableDatabase();

        RecetasOnline receta= null;
        Cursor cursor = BaseDeDatos.rawQuery("SELECT codigoRO, nombre, pais FROM recetasOnline WHERE pais='" + paises +"'",null);

        while (cursor.moveToNext()) {
            receta = new RecetasOnline();
            receta.setCodigo(cursor.getString(0));
            receta.setNombre(cursor.getString(1));
            receta.setPais(cursor.getString(2));
            listDatos.add(receta);
        }
        BaseDeDatos.close();
    }


    private void CambiarActivityMostrarRecetaOnline(){
        Intent intento = new Intent(this, MostrarRecetaOnline.class);
        intento.putExtra("codigo1",Mandar);
        startActivity(intento);
    }

}