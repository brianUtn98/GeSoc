package Dominio.Presupuesto;

import Dominio.DocumentoComercial;
import Dominio.ItemOperacion;
import Dominio.Pago.ValorMonetario;
import Dominio.Provedor;

import java.util.List;
import java.util.Optional;

public class Presupuesto {
    private Provedor provedor;
    private List<ItemOperacion> detalle;
    private Optional<DocumentoComercial> documentoComercial;

    public Presupuesto(Provedor provedor, List<ItemOperacion> detalle) {
        this.provedor = provedor;
        this.detalle = detalle;
    }

    public ValorMonetario getTotal() {
        return detalle.stream().map(ItemOperacion::getValorTotal).reduce(ValorMonetario::sumar).get(); // TODO: Revisar que pasa si no hay ningun item operacion. Deberia poder pasar eso?
    }
}
