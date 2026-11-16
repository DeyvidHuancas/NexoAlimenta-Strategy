
package com.nexoalimenta.estrategia;

import com.nexoalimenta.modelo.Comedor;
import com.nexoalimenta.modelo.Donacion;
import java.util.List;

public class EstrategiaNecesidad implements EstrategiaAsignacion {

    @Override
    public String getNombre() {
        return "Mayor necesidad social";
    }

    @Override
    public Comedor seleccionarComedor(Donacion donacion,
                                      List<Comedor> comedores) {

        Comedor seleccionado = null;

        for (Comedor comedor : comedores) {

            if (comedor.getCapacidadDisponibleKg() >= donacion.getPesoKg()) {

                if (seleccionado == null ||
                        comedor.getNivelNecesidad() > seleccionado.getNivelNecesidad()) {

                    seleccionado = comedor;
                }
            }
        }

        return seleccionado;
    }
}
