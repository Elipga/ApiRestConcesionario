package com.ApiRestConcesionario.Domain;

import com.ApiRestConcesionario.Controller.InvalidException;
import com.ApiRestConcesionario.Controller.IsEmptyException;
import com.ApiRestConcesionario.Controller.NullException;

import java.time.LocalDate;

public class Coche {
    private String matricula;
    private String marca;

    private String modelo;

    private int anyo;

    private String id;


    public Coche(String matricula, String marca, String modelo, int anyo, String id) throws IsEmptyException, InvalidException, NullException {
        //validateNull(matricula);
        this.matricula = matricula;
        this.marca = marca;
        //validateNull(modelo);
        this.modelo = modelo;
        this.id = id;
        //if((anyo < 1900) && (anyo > LocalDate.now().getYear())) throw new InvalidException("El año debe ser may");
        this.anyo = anyo;
    }

    public Coche(String matricula, String modelo) throws IsEmptyException, InvalidException, NullException {
        //validateNull(matricula);
        this.matricula = matricula;
        //validateNull(modelo);
        this.modelo = modelo;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }

    public int getAnyo() {
        return anyo;
    }

    public String getId() {
        return id;
    }


    public void validateNull(String parametro) throws NullException, IsEmptyException {
        if (parametro == null) throw new NullException(parametro + " no puede ser null");
        if (parametro.isEmpty()) throw new IsEmptyException(parametro + "no puede ser null");
    }
}
