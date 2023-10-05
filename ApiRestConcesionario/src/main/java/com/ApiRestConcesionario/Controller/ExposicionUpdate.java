package com.ApiRestConcesionario.Controller;

public class ExposicionUpdate {

    private String nombre;

    public ExposicionUpdate(String nombre) throws IsEmptyException, NullException {
        this.nombre = nombre;
    }

    public ExposicionUpdate() throws IsEmptyException, NullException {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
