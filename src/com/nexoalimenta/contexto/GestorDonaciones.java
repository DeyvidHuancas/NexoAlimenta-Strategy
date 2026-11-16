
package com.nexoalimenta.contexto;

import com.nexoalimenta.estrategia.EstrategiaAsignacion;
import com.nexoalimenta.modelo.Comedor;
import com.nexoalimenta.modelo.Donacion;
import java.util.ArrayList;
import java.util.List;

public class GestorDonaciones {

    private final List<Comedor> comedores;
    private EstrategiaAsignacion estrategia;

    public GestorDonaciones(List<Comedor> comedores,
                            EstrategiaAsignacion estrategia) {

        if (comedores == null || comedores.isEmpty()) {
            throw new IllegalArgumentException(
                    "Debe existir al menos un comedor.");
        }

        this.comedores = new ArrayList<>(comedores);
        cambiarEstrategia(estrategia);
    }

    public void cambiarEstrategia(EstrategiaAsignacion nuevaEstrategia) {

        if (nuevaEstrategia == null) {
            throw new IllegalArgumentException(
                    "La estrategia no puede ser nula.");
        }

        this.estrategia = nuevaEstrategia;
    }

    public EstrategiaAsignacion getEstrategia() {
        return estrategia;
    }

    public List<Comedor> getComedores() {
        return List.copyOf(comedores);
    }

    public Comedor seleccionarComedor(Donacion donacion) {

        if (donacion == null ||
                !Double.isFinite(donacion.getPesoKg()) ||
                donacion.getPesoKg() <= 0) {

            throw new IllegalArgumentException(
                    "El peso de la donacion debe ser positivo.");
        }

        return estrategia.seleccionarComedor(
                donacion, comedores);
    }
}
