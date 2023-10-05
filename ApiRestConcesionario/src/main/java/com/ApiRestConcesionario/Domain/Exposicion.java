package com.ApiRestConcesionario.Domain;

import com.ApiRestConcesionario.Controller.IsEmptyException;
import com.ApiRestConcesionario.Controller.NullException;
import com.ApiRestConcesionario.Service.AlreadyExistsException;

import java.util.ArrayList;
import java.util.List;

public class Exposicion {

    private String id;

    private String nombre;
    private List<Coche> cochesExpo = new ArrayList<>();

    public Exposicion(String id, String nombre) throws IsEmptyException, NullException {
        if (id == null) throw new NullException("Id no puede ser null");
        if (id.isEmpty()) throw new IsEmptyException("Id no puede ser null");
        this.id = id;
        this.nombre = nombre;
        this.cochesExpo = cochesExpo;
    }


    public void anyadirCoche(Coche c) throws AlreadyExistsException {
        for(Coche coche: cochesExpo)
            if(coche.getMatricula().equalsIgnoreCase(c.getMatricula()))
                throw new AlreadyExistsException("El coche ya se encuentra en la exposición");
        cochesExpo.add(c);
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

    public List<Coche> getCochesExpo() {
        return cochesExpo;
    }

    public void setCochesExpo(List<Coche> cochesExpo) {
        this.cochesExpo = cochesExpo;
    }

}
