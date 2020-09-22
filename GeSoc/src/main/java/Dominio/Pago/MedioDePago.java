package Dominio.Pago;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class MedioDePago {
    @Id
    @GeneratedValue
    private long medio_id;
}
