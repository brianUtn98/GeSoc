package Dominio;

import javax.persistence.*;

@Entity
public class OperacionValidada {
    @Id
    @GeneratedValue
    private long validada_id;

    @OneToOne
    private Operacion operacion;

    public OperacionValidada(){}
    public OperacionValidada(Operacion operacion){
        this.operacion = operacion;
    }

    public Operacion getOperacion() {
        return operacion;
    }
}
