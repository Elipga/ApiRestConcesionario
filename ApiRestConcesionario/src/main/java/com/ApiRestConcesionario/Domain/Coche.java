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


    public Coche(String matricula, String marca, String modelo, int anyo) throws IsEmptyException, InvalidException, NullException {
        validateNull(matricula);
        this.matricula = matricula;
        this.marca = marca;
        validateNull(modelo);
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

    public void validateNull(String parametro) throws NullException, IsEmptyException {
        if (parametro == null) throw new NullException(parametro + " no puede ser null");
        if (parametro.isEmpty()) throw new IsEmptyException(parametro + "no puede ser null");
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
