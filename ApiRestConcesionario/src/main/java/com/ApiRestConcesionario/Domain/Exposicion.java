package com.ApiRestConcesionario.Domain;

import com.ApiRestConcesionario.Controller.CocheInput;
import com.ApiRestConcesionario.Exception.*;

import java.util.HashMap;

public class Exposicion {

    private String id;
    private String nombre;
    private HashMap<String, Coche> cochesExpo = new HashMap<>();

    public Exposicion(String id) throws NullException, IsEmptyException {
        if (id == null) throw new NullException("Id no puede ser null");
        if (id.isEmpty()) throw new IsEmptyException("Id no puede ser null");
        this.id = id;
    }

    public Exposicion(String id, String nombre) throws IsEmptyException, NullException {
        if (id == null) throw new NullException("Id no puede ser null");
        if (id.isEmpty()) throw new IsEmptyException("Id no puede ser null");
        this.id = id;
        this.nombre = nombre;
        this.cochesExpo = cochesExpo;
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

    public HashMap<String, Coche> getCochesExpo() {
        return cochesExpo;
    }
}
