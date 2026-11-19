package org.example.model;

public enum Material {
    PLA("PLA", 5000, 0),
    PETG("PETG", 7000, 1),
    ABS("ABS", 7000, 2),
    TPU("TPU", 12000, 3);

    private final String nombre;
    private final int valor;
    private final int orden;

    Material(String nombre, int valor, int orden){
        this.nombre = nombre;
        this.valor = valor;
        this.orden = orden;
    }

    public String getNombre() {
        return nombre;
    }

    public int getOrden() {
        return orden;
    }

    public int getValor() {
        return valor;
    }
}


