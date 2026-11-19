package org.example.model;
import org.example.exception.GuiaInvalidaException;
import org.example.exception.TiempoImpresionInvalidoException;

import java.util.Objects;

public abstract class Orden {
    private final String guia;
    private final Material material;
    private final double tiempoImpresion;

    protected Orden (String guia, Material material, double tiempoImpresion){
        if (guia == null ||!guia.matches("IMP\\d{3}")){
            throw new GuiaInvalidaException("guia invalida "+ guia + " debe ser IMP y tres digitos");
        }

        if (!Double.isFinite(tiempoImpresion) || tiempoImpresion < 0 || tiempoImpresion > 12 ){
           throw new TiempoImpresionInvalidoException("tiempo de impresion debe ser de entre 0 y 12 horass");
        }

        if (material == null){
            throw new IllegalArgumentException("el material no puede ser nullo");
        }

        this.guia = guia;
        this.material = material;
        this.tiempoImpresion = tiempoImpresion;
    }

    public final String getGuia() {
        return guia;
    }

    public final double getTiempoImpresion() {
        return tiempoImpresion;
    }

    public final Material getMaterial() {
        return material;
    }

    public final double calcularBase() { return material.getValor() + 2000 * tiempoImpresion; }
    public abstract double calcularCosto();
    public abstract String getServicio();
    public abstract String getAjuste();

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Orden)) return false;
        Orden otro = (Orden) obj;
        return guia.equals(otro.guia);
    }

    @Override
    public final int hashCode() { return Objects.hash(guia); }

    @Override
    public String toString() {
        return getServicio() + " " + guia + " (" + material.getNombre() + ", " + tiempoImpresion + " h)";
    }
}


