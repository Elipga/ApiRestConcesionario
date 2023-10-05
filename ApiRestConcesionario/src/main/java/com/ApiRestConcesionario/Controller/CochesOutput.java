package com.ApiRestConcesionario.Controller;

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


    public CochesOutput(String matricula) throws IsEmptyException, NullException {
        if (matricula == null) throw new NullException("Matrícula no puede ser null");
        if (matricula.isEmpty()) throw new IsEmptyException("Matrícula no puede ser null");
        this.matricula = matricula;
        this.marca = "marca";
        this.modelo = "modelo";
        this.anyo = 1900;
    }

    public CochesOutput(String matricula, String marca) throws IsEmptyException, NullException {
        if (matricula == null) throw new NullException("Matrícula no puede ser null");
        if (matricula.isEmpty()) throw new IsEmptyException("Matrícula no puede ser null");
        this.matricula = matricula;
        if (marca == null) throw new NullException("Marca no puede ser null");
        if (marca.isEmpty()) throw new IsEmptyException("Marca no puede ser null");
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
}


