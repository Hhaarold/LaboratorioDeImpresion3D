package org.example.model;

import org.example.exception.AcabadoInvalidoException;

public final class ConAcabado extends Orden implements ManejoEspecial {
    private final String acabado;

    public ConAcabado(String guia,  double tiempoImpresion, Material material,String acabado) {
        super(guia, material, tiempoImpresion);
        if (acabado == null || acabado.isBlank()) {
            throw new AcabadoInvalidoException("Orden " + guia + ": el acabado es obligatorio.");
        }
        this.acabado = acabado.trim();
    }

    public String getAcabado() { return acabado; }
    @Override public double calcularCosto() { return calcularBase() + 8000; }
    @Override public String getServicio() { return "Con acabado (" + acabado + ")"; }
    @Override public String getAjuste() { return "+8000"; }
    @Override public String getAdvertencia() { return getGuia() + " · Acabado (" + acabado + ")"; }
}
