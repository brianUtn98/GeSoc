package Dominio.Presupuesto;

import Dominio.DocumentoComercial;
import Dominio.ItemOperacion;
import Dominio.Provedor;

import java.util.List;
import java.util.Optional;

public class Presupuesto {
    private Provedor provedor;
    private List<ItemOperacion> detalle;;
    private Optional<DocumentoComercial> documentoComercial;

    public Presupuesto(Provedor provedor, List<ItemOperacion> detalle) {
        this.provedor = provedor;
        this.detalle = detalle;
    }

    public Integer getTotal(){
        return detalle.stream().mapToInt(ItemOperacion::getValorTotal).sum();
    }
}
