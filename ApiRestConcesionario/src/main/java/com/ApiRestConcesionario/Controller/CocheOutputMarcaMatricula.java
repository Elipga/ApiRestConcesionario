package com.ApiRestConcesionario.Controller;

import com.ApiRestConcesionario.Domain.Coche;

public class CocheOutputMarcaMatricula {
    private String matricula;
    private String marca;


    public CocheOutputMarcaMatricula(String matricula, String marca) {
        this.matricula = matricula;
        this.marca = marca;
    }

    public static CocheOutputMarcaMatricula getCoche(Coche c) {
        return new CocheOutputMarcaMatricula(c.getMatricula(), c.getMarca());
    }

    public String getMarca() {
        return marca;
    }

    public String getMatricula() {
        return matricula;
    }
}
