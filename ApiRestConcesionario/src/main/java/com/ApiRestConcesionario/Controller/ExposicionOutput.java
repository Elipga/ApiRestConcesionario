package com.ApiRestConcesionario.Controller;

public class ExposicionOutput {

    private String id;

    private String nombre;

    public ExposicionOutput(String id) throws IsEmptyException, NullException {
        validateNull(id);
        this.id = id;
        this.nombre = "nombre";
    }

    public ExposicionOutput(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void validateNull(String parametro) throws NullException, IsEmptyException {
        if (parametro == null) throw new NullException(parametro + " no puede ser null");
        if (parametro.isEmpty()) throw new IsEmptyException(parametro + "no puede ser null");
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
