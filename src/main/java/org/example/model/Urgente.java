package org.example.model;

public final class Urgente extends Orden {
    public Urgente(String guia, double tiempoImpresion, Material material) { super(guia, material, tiempoImpresion); }
    @Override public double calcularCosto() { return calcularBase() * 1.5; }
    @Override public String getServicio() { return "Urgente"; }
    @Override public String getAjuste() { return "+50%"; }
}
