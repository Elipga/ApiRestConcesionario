package com.ApiRestConcesionario.Controller;

import com.ApiRestConcesionario.Domain.Coche;
import com.ApiRestConcesionario.Exception.InvalidException;
import com.ApiRestConcesionario.Exception.IsEmptyException;
import com.ApiRestConcesionario.Exception.NullException;

import java.time.LocalDate;



public class CochesOutput {
    private String matricula;
    private String marca;
    private String modelo;
    private int anyo;


    public CochesOutput(String matricula, String marca, String modelo, int anyo) throws NullException, IsEmptyException, InvalidException {
        if (matricula == null) throw new NullException("Matrícula no puede ser null");
        if (matricula.isEmpty()) throw new IsEmptyException("Matrícula no puede ser null");
        this.matricula = matricula;
        this.marca = marca;
        if (modelo == null) throw new NullException("Modelo no puede ser null");
        if (modelo.isEmpty()) throw new IsEmptyException("Modelo no puede ser null");
        this.modelo = modelo;
        if ((anyo < 1900) || (anyo > LocalDate.now().getYear())) throw new InvalidException("El año debe ser may");
        this.anyo = anyo;
    }

    public static CochesOutput getCoche(Coche coche) throws IsEmptyException, InvalidException, NullException {
        return new CochesOutput(coche.getMatricula(),coche.getMarca(),coche.getModelo(), coche.getAnyo());
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
}


