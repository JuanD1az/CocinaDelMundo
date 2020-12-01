package com.app.cocinadelmundo;

import java.io.Serializable;

public class RecetasOnline implements Serializable {
    private String Codigo;
    private String Nombre;
    private String Ingredientes;
    private String Pasos;
    private String Pais;

    public RecetasOnline() {
    }

    public RecetasOnline(String codigo, String nombre, String ingredientes, String pasos, String pais) {
        this.Codigo = codigo;
        this.Nombre = nombre;
        this.Ingredientes = ingredientes;
        this.Pasos = pasos;
        this.Pais = pais;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public String getPais() {
        return Pais;
    }

    public void setPais(String pais) {
        Pais = pais;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getIngredientes() {
        return Ingredientes;
    }

    public void setIngredientes(String ingredientes) {
        Ingredientes = ingredientes;
    }

    public String getPasos() {
        return Pasos;
    }

    public void setPasos(String pasos) {
        this.Pasos = pasos;
    }
}