package com.ApiRestConcesionario.Controller;

import com.ApiRestConcesionario.Exception.IsEmptyException;
import com.ApiRestConcesionario.Exception.NullException;

public class ExposicionOutputNombre {

    private String nombre;

    public ExposicionOutputNombre(String nombre){
        this.nombre = nombre;

    }

    public String getNombre() {
        return nombre;
    }
}
