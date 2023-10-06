package com.ApiRestConcesionario.Controller;

import com.ApiRestConcesionario.Exception.IsEmptyException;
import com.ApiRestConcesionario.Exception.NullException;

public class ExposicionInput {

    private String id;

    private String nombre;

    public ExposicionInput(String id, String nombre) throws NullException, IsEmptyException {
        if (id == null) throw new NullException("Id no puede ser null");
        if (id.isEmpty()) throw new IsEmptyException("Id no puede ser null");
        this.id = id;
        if (nombre == null) throw new NullException("Matrícula no puede ser null");
        if (nombre.isEmpty()) throw new IsEmptyException("Matrícula no puede ser null");
        this.nombre = nombre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
