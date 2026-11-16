
package com.nexoalimenta.estrategia;

import com.nexoalimenta.modelo.Comedor;
import com.nexoalimenta.modelo.Donacion;
import java.util.List;

public class EstrategiaDistancia implements EstrategiaAsignacion {

    @Override
    public String getNombre() {
        return "Menor distancia";
    }

    @Override
    public Comedor seleccionarComedor(Donacion donacion,
                                      List<Comedor> comedores) {

        Comedor seleccionado = null;

        for (Comedor comedor : comedores) {

            if (comedor.getCapacidadDisponibleKg() >= donacion.getPesoKg()) {

                if (seleccionado == null ||
                        comedor.getDistanciaKm() < seleccionado.getDistanciaKm()) {

                    seleccionado = comedor;
                }
            }
        }

        return seleccionado;
    }
}
