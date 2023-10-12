package com.ApiRestConcesionario.Controller;

public class CocheUpdate {
    private String marca;
    private String modelo;

    public CocheUpdate() {
        this.marca = marca;
    }

    public CocheUpdate(String marca, String modelo) {
        this.marca = marca;
        this.modelo = modelo;
    }

    public String getModelo() {
        return modelo;
    }

    public String getMarca() {
        return marca;
    }
}
