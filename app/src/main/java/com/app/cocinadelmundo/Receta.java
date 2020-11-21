package com.app.cocinadelmundo;

import java.io.Serializable;

public class Receta implements Serializable {
    private String nombre;
    private String codigo;

    public Receta(String nombre, String codigo) {
        this.nombre = nombre;
        this.codigo = codigo;
    }

    public Receta(){

    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }


}
