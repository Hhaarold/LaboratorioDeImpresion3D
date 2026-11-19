- La clase abstracta `Orden` guarda guía, tiempo impresion y material inmutables, valida su construcción y declara el cálculo de costo abstracto.
- `Estandar`, `Urgente` y `ConAcabado` implementan cada fórmula a través de sobrescritura
- `Material` es un `enum` con valor y orden , ambos definidos en su constructor.
- `Urgente` implementa `ManejoEspecial`. El manifiesto revisa `instanceof ManejoEspecial` y realiza un cast explícito a la interfaz para obtener advertencias.
- `ManifiestoObligatorio` posee una `List<Orden>` encapsulada, evita guías repetidas y verifica 24 h **antes** de añadir. Los estados anteriores se conservan si algo falla.
- `OrdenadorOrdenes` es una utilidad con `Comparator`: primero `Urgente`, después orden de Materia y por último guía ascendente.
- Cinco excepciones personalizadas comunican errores específicos
- La igualdad (`equals`/`hashCode`) considera solamente la guía, también entre clases de servicio diferentes.

## Principios SOLID

**O — Abierto/cerrado (OCP).** Para crear un nuevo servicio, como Refrigerado, se puede añadir otra subclase de `Ordener` que sobrescriba `calcularCosto()`, sin editar `liquidarRutaObligatoria()` ni el código que imprime el costo. Si necesita advertencia, implementa `ManejoEspecial`.

**S — Responsabilidad única (SRP).** `Orden` y sus subclases conocen la información y el precio de cada envío; `ManifiestoObligatorio` controla paquetes y totales; `OrdenadorOrdenes` se ocupa del ordenamiento. Estas tareas se separan para que un cambio en el criterio de orden no obligue a reescribir los cálculos de costos.
