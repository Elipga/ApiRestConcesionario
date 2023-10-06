package com.ApiRestConcesionario.Controller;

import com.ApiRestConcesionario.Exception.IsEmptyException;
import com.ApiRestConcesionario.Exception.NullException;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_EMPTY)

public class ExposicionOutput {

    private String id;
    private String nombre;

    public ExposicionOutput(String id) throws IsEmptyException, NullException {
        if (id == null) throw new NullException("Id no puede ser null");
        if (id.isEmpty()) throw new IsEmptyException("Id no puede ser null");
        this.id = id;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
