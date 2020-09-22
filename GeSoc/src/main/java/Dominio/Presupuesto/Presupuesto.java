package Dominio.Presupuesto;

import Dominio.DocumentoComercial;
import Dominio.ItemOperacion;
import Dominio.Pago.ValorMonetario;
import Dominio.Provedor;

import javax.persistence.*;
import java.util.List;
import java.util.Optional;

@Entity
public class Presupuesto {
    @Id
    @GeneratedValue
    private long presupuesto_id;

    @ManyToOne
    private Provedor provedor;

    @Transient
    private List<ItemOperacion> detalle;

    @Transient
    private Optional<DocumentoComercial> documentoComercial;

    public Presupuesto(Provedor provedor, List<ItemOperacion> detalle) {
        this.provedor = provedor;
        this.detalle = detalle;
    }

    public ValorMonetario getTotal() {
        return detalle.stream().map(ItemOperacion::getValorTotal).reduce(ValorMonetario::sumar).get(); // TODO: Revisar que pasa si no hay ningun item operacion. Deberia poder pasar eso?
    }
}
