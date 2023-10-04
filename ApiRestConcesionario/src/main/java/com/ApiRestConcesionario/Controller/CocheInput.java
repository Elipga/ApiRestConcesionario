package com.ApiRestConcesionario.Controller;

import com.ApiRestConcesionario.Domain.Coche;

public class CocheInput {
    private String matricula;
    private String modelo;


    public CocheInput(String matricula, String modelo) throws IsEmptyException, NullException, InvalidException {
        validateNull(matricula);
        this.matricula = matricula;
        validateNull(modelo);
        this.modelo = modelo;
    }

    public String getMatricula() {
        return matricula;
    }

    public String getModelo() {
        return modelo;
    }

    public void validateNull(String parametro) throws NullException, IsEmptyException {
        if (parametro == null) throw new NullException(parametro + " no puede ser null");
        if (parametro.isEmpty()) throw new IsEmptyException(parametro + "no puede ser null");
    }
}
