package com.ApiRestConcesionario.Controller;

import com.ApiRestConcesionario.Exception.IsEmptyException;
import com.ApiRestConcesionario.Exception.NullException;


public class ExposicionOutput {

    private String id;

    public ExposicionOutput(String id) throws IsEmptyException, NullException {
        if (id == null) throw new NullException("Id no puede ser null");
        if (id.isEmpty()) throw new IsEmptyException("Id no puede ser null");
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

}
