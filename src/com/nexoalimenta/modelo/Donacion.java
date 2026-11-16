
package com.nexoalimenta.modelo;

public class Donacion {

    private String codigo;
    private String alimento;
    private double pesoKg;

    public Donacion(String codigo, String alimento, double pesoKg) {
        this.codigo = codigo;
        this.alimento = alimento;
        this.pesoKg = pesoKg;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getAlimento() {
        return alimento;
    }

    public double getPesoKg() {
        return pesoKg;
    }

    @Override
    public String toString() {
        return "Donacion: " + codigo +
                " | Alimento: " + alimento +
                " | Peso: " + pesoKg + " kg";
    }
}
