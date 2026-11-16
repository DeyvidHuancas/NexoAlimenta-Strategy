
package com.nexoalimenta.estrategia;

import com.nexoalimenta.modelo.Comedor;
import com.nexoalimenta.modelo.Donacion;
import java.util.List;

public class EstrategiaCapacidad implements EstrategiaAsignacion {

    @Override
    public String getNombre() {
        return "Mayor capacidad disponible";
    }

    @Override
    public Comedor seleccionarComedor(Donacion donacion,
                                      List<Comedor> comedores) {

        Comedor seleccionado = null;

        for (Comedor comedor : comedores) {

            if (comedor.getCapacidadDisponibleKg() >= donacion.getPesoKg()) {

                if (seleccionado == null ||
                        comedor.getCapacidadDisponibleKg() >
                                seleccionado.getCapacidadDisponibleKg()) {

                    seleccionado = comedor;
                }
            }
        }

        return seleccionado;
    }
}
