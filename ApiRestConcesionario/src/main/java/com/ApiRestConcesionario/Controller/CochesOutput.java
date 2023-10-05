package com.ApiRestConcesionario.Controller;

import java.time.LocalDate;

public class CochesOutput {
    private String matricula;
    private String marca;
    private String modelo;

    private int anyo;

    public CochesOutput(String matricula, String marca, String modelo, int anyo) throws NullException, IsEmptyException, InvalidException {
        validateNull(matricula);
        this.matricula = matricula;
        this.marca = marca;
        validateNull(modelo);
        this.modelo = modelo;
        if ((anyo < 1900) || (anyo > LocalDate.now().getYear())) throw new InvalidException("El año debe ser may");
        this.anyo = anyo;
    }


    public CochesOutput(String matricula) throws IsEmptyException, NullException {
        validateNull(matricula);
        this.matricula = matricula;
    }

    public CochesOutput(String matricula, String marca) throws IsEmptyException, NullException {
        validateNull(matricula);
        this.matricula = matricula;
        validateNull(marca);
        this.marca = marca;
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

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public void setAnyo(int anyo) {
        this.anyo = anyo;
    }

    public void validateNull(String parametro) throws NullException, IsEmptyException {
        if (parametro == null) throw new NullException(parametro + " no puede ser null");
        if (parametro.isEmpty()) throw new IsEmptyException(parametro + "no puede ser null");
    }


}


