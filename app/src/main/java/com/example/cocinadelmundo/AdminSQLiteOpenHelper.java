package com.example.cocinadelmundo;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;


public class AdminSQLiteOpenHelper extends SQLiteOpenHelper{
    public AdminSQLiteOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase BaseDeDatos) {
        BaseDeDatos.execSQL("create table recetas(codigoR int primary key, nombre text, ingredientes text, pasos text)");
        BaseDeDatos.execSQL("create table recetasOnline(codigoRO int primary key, nombre text, ingredientes text, pasos text, pais text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase BaseDeDatos, int i, int i1) {
        BaseDeDatos.execSQL("drop table if exists recetas");
        BaseDeDatos.execSQL("drop table if exists recetasOnline");

        BaseDeDatos.execSQL("create table recetas(codigoR int primary key, nombre text, ingredientes text, pasos text)");
        BaseDeDatos.execSQL("create table recetasOnline(codigoRO int primary key, nombre text, ingredientes text, pasos text, pais text)");

    }
}
