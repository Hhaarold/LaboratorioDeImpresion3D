package org.example.service;

import org.example.exception.GuiaDuplicadaException;
import org.example.exception.TiempoExcedidoException;
import org.example.model.ManejoEspecial;
import org.example.model.Orden;
import org.example.util.OrdenadorOrdenes;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public final class ManifiestoObligatorio {
    private static int consecutivo = 0;
    private static final double MAX_T = 24.0;

    private final String codigo;
    private final String operador;
    private final List<Orden> ordenes = new ArrayList<>();
    private double tiempoTotal = 0;

    public ManifiestoObligatorio(String operador) {
        if (operador == null || operador.isBlank()) {
            throw new IllegalArgumentException("El nombre del mensajero es obligatorio.");
        }
        this.operador = operador.trim();
        this.codigo = "T-" + (++consecutivo);
    }

    public double calcularTiempoTotal() { return tiempoTotal; }


    public void agregarOrden(Orden orden) {
        if (orden == null) throw new IllegalArgumentException("No puede registrarse una orden nula.");
        if (ordenes.contains(orden)) {
            throw new GuiaDuplicadaException("Orden " + orden.getGuia() + ": guía duplicada  " + codigo + ".");
        }
        if (tiempoTotal + orden.getTiempoImpresion()> MAX_T ) {
            throw new TiempoExcedidoException("orden " + orden.getGuia() + ": excedería 24 horas " + codigo +
                    " (actual " + tiempoTotal + " h, nuevo " + orden.getTiempoImpresion() + " h).");
        }

        ordenes.add(orden);
        tiempoTotal += orden.getTiempoImpresion();
    }

    public double liquidarRutaObligatoria() {
        double total = 0;
        for (Orden orden : ordenes) total += orden.calcularCosto();
        return total;
    }

    public void imprimirManifiesto() {
        System.out.println("\nDespacho " + codigo + " – operador: " + operador);
        System.out.printf("%-9s %-23s %-9s %9s %13s %9s %13s%n",
                "Guía", "Servicio", "Material", "Tiempo", "Costo base", "Ajuste", "Costo envío");
        List<String> advertencias = new ArrayList<>();
        for (Orden o : OrdenadorOrdenes.ordenar(ordenes)) {
            System.out.printf(Locale.forLanguageTag("es-CO"), "%-9s %-23s %-9s %7.1f h %13.0f %9s %13.0f%n",
                    o.getGuia(), o.getServicio(), o.getMaterial().getNombre(), o.getTiempoImpresion(),
                    o.calcularBase(), o.getAjuste(), o.calcularCosto());
            if (o instanceof ManejoEspecial) {
                ManejoEspecial especial = (ManejoEspecial) o;
                advertencias.add(especial.getAdvertencia());
            }
        }
        System.out.printf(Locale.forLanguageTag("es-CO"), "Tiempo total: %.1f h · Costo total: $%,.0f%n",
                calcularTiempoTotal(), liquidarRutaObligatoria());
        System.out.println("acabado: " + (advertencias.isEmpty() ? "Ninguna" : String.join(" | ", advertencias)));
    }
}
