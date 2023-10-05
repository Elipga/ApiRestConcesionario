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

    public void validateNull(String parametro) throws NullException, IsEmptyException {
        if (parametro == null) throw new NullException(parametro + " no puede ser null");
        if (parametro.isEmpty()) throw new IsEmptyException(parametro + "no puede ser null");
    }
}
