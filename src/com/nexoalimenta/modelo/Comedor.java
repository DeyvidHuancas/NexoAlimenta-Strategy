
package com.nexoalimenta.modelo;

public class Comedor {

    private String nombre;
    private double distanciaKm;
    private int nivelNecesidad;
    private double capacidadDisponibleKg;

    public Comedor(String nombre, double distanciaKm,
                   int nivelNecesidad, double capacidadDisponibleKg) {
        this.nombre = nombre;
        this.distanciaKm = distanciaKm;
        this.nivelNecesidad = nivelNecesidad;
        this.capacidadDisponibleKg = capacidadDisponibleKg;
    }

    public String getNombre() {
        return nombre;
    }

    public double getDistanciaKm() {
        return distanciaKm;
    }

    public int getNivelNecesidad() {
        return nivelNecesidad;
    }

    public double getCapacidadDisponibleKg() {
        return capacidadDisponibleKg;
    }

    @Override
    public String toString() {
        return nombre +
                " | Distancia: " + distanciaKm + " km" +
                " | Necesidad: " + nivelNecesidad +
                " | Capacidad: " + capacidadDisponibleKg + " kg";
    }
}
