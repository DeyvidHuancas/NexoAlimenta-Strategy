
package com.nexoalimenta.app;

import com.nexoalimenta.contexto.GestorDonaciones;
import com.nexoalimenta.estrategia.EstrategiaAsignacion;
import com.nexoalimenta.estrategia.EstrategiaCapacidad;
import com.nexoalimenta.estrategia.EstrategiaDistancia;
import com.nexoalimenta.estrategia.EstrategiaNecesidad;
import com.nexoalimenta.modelo.Comedor;
import com.nexoalimenta.modelo.Donacion;
import java.util.List;
import java.util.Scanner;

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

        List<Comedor> comedores = List.of(
                new Comedor("Comedor Sol", 3, 5, 50),
                new Comedor("Comedor Vida", 12, 10, 25),
                new Comedor("Comedor Esperanza", 7, 7, 90)
        );

        GestorDonaciones gestor = new GestorDonaciones(
                comedores, new EstrategiaDistancia()
        );

        boolean activo = true;

        while (activo) {

            System.out.println("\n===== NEXOALIMENTA =====");
            System.out.println("Estrategia actual: " +
                    gestor.getEstrategia().getNombre());

            System.out.println("1. Ver comedores");
            System.out.println("2. Simular asignacion de una donacion");
            System.out.println("3. Cambiar estrategia");
            System.out.println("4. Comparar las tres estrategias");
            System.out.println("0. Salir");
            System.out.print("Selecciona una opcion: ");

            String opcion = scanner.nextLine().trim();

            switch (opcion) {
                case "1" -> mostrarComedores(gestor);
                case "2" -> simularDonacion(gestor);
                case "3" -> cambiarEstrategia(gestor);
                case "4" -> compararEstrategias(gestor);
                case "0" -> {
                    activo = false;
                    System.out.println(
                            "Gracias por utilizar NexoAlimenta.");
                }
                default -> System.out.println(
                        "Opcion no valida.");
            }
        }
    }

    private static void mostrarComedores(GestorDonaciones gestor) {

        System.out.println("\n--- COMEDORES DISPONIBLES ---");

        for (Comedor comedor : gestor.getComedores()) {
            System.out.println(comedor);
        }
    }

    private static void simularDonacion(GestorDonaciones gestor) {

        System.out.println("\n--- SIMULAR DONACION ---");

        String codigo = leerTexto("Codigo de donacion");
        String alimento = leerTexto("Alimento");
        double peso = leerPeso();

        Donacion donacion = new Donacion(codigo, alimento, peso);
        Comedor seleccionado = gestor.seleccionarComedor(donacion);

        System.out.println("\n" + donacion);
        System.out.println("Estrategia: " +
                gestor.getEstrategia().getNombre());

        if (seleccionado == null) {
            System.out.println(
                    "Resultado: no existe un comedor con capacidad suficiente.");
        } else {
            System.out.println("Comedor recomendado: " +
                    seleccionado.getNombre());
        }

        System.out.println(
                "Esta es una simulacion; no se modifica la capacidad disponible.");
    }

    private static void cambiarEstrategia(GestorDonaciones gestor) {

        System.out.println("\n--- SELECCION DE ESTRATEGIA ---");

        System.out.println("1. Menor distancia");
        System.out.println("2. Mayor necesidad social");
        System.out.println("3. Mayor capacidad disponible");
        System.out.print("Elige una estrategia: ");

        String opcion = scanner.nextLine().trim();

        switch (opcion) {
            case "1" -> gestor.cambiarEstrategia(
                    new EstrategiaDistancia());
            case "2" -> gestor.cambiarEstrategia(
                    new EstrategiaNecesidad());
            case "3" -> gestor.cambiarEstrategia(
                    new EstrategiaCapacidad());
            default -> {
                System.out.println(
                        "Opcion no valida. Se mantiene la estrategia actual.");
                return;
            }
        }

        System.out.println("Estrategia actualizada: " +
                gestor.getEstrategia().getNombre());
    }

    private static void compararEstrategias(GestorDonaciones gestor) {

        Donacion ejemplo = new Donacion(
                "DEMO-001", "Arroz", 20);

        EstrategiaAsignacion anterior = gestor.getEstrategia();

        List<EstrategiaAsignacion> estrategias = List.of(
                new EstrategiaDistancia(),
                new EstrategiaNecesidad(),
                new EstrategiaCapacidad()
        );

        System.out.println(
                "\n--- COMPARACION PARA 20 KG DE ARROZ ---");

        for (EstrategiaAsignacion estrategia : estrategias) {

            gestor.cambiarEstrategia(estrategia);

            Comedor resultado = gestor.seleccionarComedor(ejemplo);

            String nombre = resultado == null
                    ? "Sin comedor elegible"
                    : resultado.getNombre();

            System.out.println(
                    estrategia.getNombre() + ": " + nombre);
        }

        gestor.cambiarEstrategia(anterior);

        System.out.println(
                "Se restauro la estrategia que estabas usando.");
    }

    private static String leerTexto(String campo) {

        while (true) {

            System.out.print(campo + ": ");
            String valor = scanner.nextLine().trim();

            if (!valor.isEmpty()) {
                return valor;
            }

            System.out.println(
                    "Este campo no puede quedar vacio.");
        }
    }

    private static double leerPeso() {

        while (true) {

            System.out.print("Peso en kg: ");
            String entrada = scanner.nextLine()
                    .trim().replace(',', '.');

            try {

                double peso = Double.parseDouble(entrada);

                if (Double.isFinite(peso) && peso > 0) {
                    return peso;
                }

            } catch (NumberFormatException e) {
                System.out.println(
                        "Debes ingresar un numero valido.");
                continue;
            }

            System.out.println(
                    "Ingresa un peso positivo y valido.");
        }
    }
}
