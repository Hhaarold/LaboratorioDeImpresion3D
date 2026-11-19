package org.example;

import org.example.model.*;
import org.example.service.ManifiestoObligatorio;

public final class Main {
    private Main() { }

    @FunctionalInterface
    private interface Intento { void ejecutar(); }

    private static void intentar(String etiqueta, Intento intento) {
        try {
            intento.ejecutar();
            System.out.println("OK: " + etiqueta);
        } catch (RuntimeException e) {
            System.out.println("RECHAZADO [" + etiqueta + "]: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        ManifiestoObligatorio valentina = new ManifiestoObligatorio("valentina");
        intentar("IMP201", () -> valentina.agregarOrden(new Estandar("IMP201", 4, Material.TPU)));
        intentar("IMP202", () -> valentina.agregarOrden(new Urgente("IMP202", 1.5, Material.PETG)));
        intentar("IMP203", () -> valentina.agregarOrden(new ConAcabado("IMP203", 2, Material.PLA, "lijado")));
        intentar("IMP204", () -> valentina.agregarOrden(new Estandar("IMP204", 6, Material.PLA)));
        intentar("IMP205", () -> valentina.agregarOrden(new Urgente("IMP205", 6, Material.TPU)));
        intentar("IMP206", () -> valentina.agregarOrden(new ConAcabado("IMP206", 3.5, Material.ABS, "pintura")));
        valentina.imprimirManifiesto();


        intentar("IMP301", () -> new Estandar("IMP301", -2, Material.PLA));
        intentar("IMP302", () -> new ConAcabado("IMP302", 2, Material.PETG, ""));
        intentar("IMP4A2", () -> new Estandar("IMP4A2", 3, Material.ABS));
        intentar("IMP303", () -> new Urgente("IMP303", 14, Material.PETG));


        ManifiestoObligatorio juan = new ManifiestoObligatorio("juan");
        intentar("IMP401", () -> juan.agregarOrden(new Estandar("IMP401", 10, Material.PLA)));
        intentar("IMP402", () -> juan.agregarOrden(new Urgente("IMP402", 8, Material.ABS)));
        intentar("IMP403", () -> juan.agregarOrden(new ConAcabado("IMP403", 7, Material.PETG, "pintura")));
        intentar("IMP404", () -> juan.agregarOrden(new Estandar("IMP404", 6, Material.TPU)));
        juan.imprimirManifiesto();

        ManifiestoObligatorio sara = new ManifiestoObligatorio("sara");
        intentar("IMP501 ", () -> sara.agregarOrden(new ConAcabado("IMP501", 3, Material.ABS, "barniz")));
        intentar("IMP501 ", () -> sara.agregarOrden(new Estandar("IMP501", 1, Material.PLA)));
        intentar("IMP502 ", () -> sara.agregarOrden(new Urgente("IMP502", 2, Material.PLA)));
        sara.imprimirManifiesto();
    }
}
