package com.ApiRestConcesionario.Domain;

import com.ApiRestConcesionario.Controller.CocheInput;
import com.ApiRestConcesionario.Controller.CochesOutput;
import com.ApiRestConcesionario.Exception.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

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

    public void anyadirCoche(CocheInput cocheInput) throws AlreadyExistsException, IsEmptyException, InvalidException, NullException, NotExistsException {
        Coche c = CocheInput.getCoche(cocheInput);
        if (cochesExpo.containsKey(cocheInput.getMatricula())) {
            throw new AlreadyExistsException("El coche ya existe");
        }
        cochesExpo.put(cocheInput.getMatricula(), c);
    }

    public Coche buscarCoche(String matricula) throws NotExistsException {
        if(cochesExpo.containsKey(matricula))
            return cochesExpo.get(matricula);
        throw new NotExistsException("El coche no se encuentra en la exposición");
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
