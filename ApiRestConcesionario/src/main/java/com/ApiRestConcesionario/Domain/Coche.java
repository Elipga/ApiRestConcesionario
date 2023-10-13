package com.ApiRestConcesionario.Domain;

import com.ApiRestConcesionario.Exception.InvalidException;
import com.ApiRestConcesionario.Exception.IsEmptyException;
import com.ApiRestConcesionario.Exception.NullException;

public class Coche {
    private String matricula;
    private String marca;
    private String modelo;
    private int anyo;

    public Coche(String matricula) throws NullException, IsEmptyException {
        if (matricula == null) throw new NullException("Matrícula no puede ser null");
        if (matricula.isEmpty()) throw new IsEmptyException("Matrícula no puede ser null");
        this.matricula = matricula;
    }

    public Coche(String matricula, String marca, String modelo, int anyo) throws IsEmptyException, InvalidException, NullException {
        if (matricula == null) throw new NullException("Matrícula no puede ser null");
        if (matricula.isEmpty()) throw new IsEmptyException("Matrícula no puede ser null");
        this.matricula = matricula;
        this.marca = marca;
        this.modelo = modelo;
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

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }
}
