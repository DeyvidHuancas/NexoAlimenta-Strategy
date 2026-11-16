
package com.nexoalimenta.estrategia;

import com.nexoalimenta.modelo.Comedor;
import com.nexoalimenta.modelo.Donacion;
import java.util.List;

public interface EstrategiaAsignacion {

    String getNombre();

    Comedor seleccionarComedor(
            Donacion donacion,
            List<Comedor> comedores
    );
}
