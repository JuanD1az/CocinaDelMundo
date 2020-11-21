package com.app.cocinadelmundo;

import java.io.Serializable;

public class RecetasOnline implements Serializable {

    private String codigo;
    private String nombre;
    private String pais;


    public RecetasOnline(String codigo,String nombre, String pais) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.pais = pais;
    }

    public RecetasOnline(){

    }

    public String getCodigo() {
        return codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public String getPais() {
        return pais;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }
}
