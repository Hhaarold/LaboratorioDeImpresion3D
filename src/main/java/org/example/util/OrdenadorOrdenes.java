package org.example.util;

import org.example.model.Urgente;
import org.example.model.Orden;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public final class OrdenadorOrdenes {
    private OrdenadorOrdenes() { }

    public static final Comparator<Orden> POR_ENTREGA =
            Comparator.comparingInt((Orden o) -> o instanceof Urgente ? 0 : 1)
                    .thenComparingInt(o -> o.getMaterial().getOrden())
                    .thenComparing(Orden::getGuia);

    public static List<Orden> ordenar(List<Orden> Ordens) {
        List<Orden> copia = new ArrayList<>(Ordens);
        copia.sort(POR_ENTREGA);
        return copia;
    }


}

