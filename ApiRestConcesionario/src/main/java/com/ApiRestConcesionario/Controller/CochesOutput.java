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
        if ((anyo < 1900) && (anyo > LocalDate.now().getYear())) throw new InvalidException("El año debe ser may");
        this.anyo = anyo;
    }

    public CochesOutput(String matricula) throws IsEmptyException, InvalidException, NullException {
        validateNull(matricula);
        this.matricula = matricula;
    }

    public void validateNull(String parametro) throws NullException, IsEmptyException {
        if (parametro == null) throw new NullException(parametro + " no puede ser null");
        if (parametro.isEmpty()) throw new IsEmptyException(parametro + "no puede ser null");
    }
}


