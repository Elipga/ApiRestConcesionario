package com.ApiRestConcesionario.Domain;

import com.ApiRestConcesionario.Exception.InvalidException;
import com.ApiRestConcesionario.Exception.IsEmptyException;
import com.ApiRestConcesionario.Exception.NullException;

import java.time.LocalDate;

public class Coche {
    private String matricula;
    private String marca;

    private String modelo;

    private int anyo;


    public Coche(String matricula, String marca, String modelo, int anyo) throws IsEmptyException, InvalidException, NullException {
        if (matricula == null) throw new NullException("Matrícula no puede ser null");
        if (matricula.isEmpty()) throw new IsEmptyException("Matrícula no puede ser null");
        this.matricula = matricula;
        this.marca = marca;
        if (modelo == null) throw new NullException("Modelo no puede ser null");
        if (modelo.isEmpty()) throw new IsEmptyException("Modelo no puede ser null");
        this.modelo = modelo;
        if((anyo < 1900) && (anyo > LocalDate.now().getYear())) throw new InvalidException("El año debe ser may");
        this.anyo = anyo;
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

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setAnyo(int anyo) {
        this.anyo = anyo;
    }
}
