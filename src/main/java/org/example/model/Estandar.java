package org.example.model;

public final class Estandar extends Orden {
    public Estandar(String guia, double tiempoImpresion, Material material) { super(guia, material, tiempoImpresion); }
    @Override public double calcularCosto() { return calcularBase(); }
    @Override public String getServicio() { return "Estándar"; }
    @Override public String getAjuste() { return "—"; }
}