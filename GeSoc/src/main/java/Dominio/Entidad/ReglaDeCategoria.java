package Dominio.Entidad;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.persistence.Transient;

import Dominio.Operacion;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class ReglaDeCategoria {
	
	@Id
    @GeneratedValue
    private long regla_id;
	
	protected int montoMaximo;
	
	@Transient
    protected String tipo;
	
    public abstract void sePuedeAgregarOperacion(EntidadOrganizacional entidad,Operacion operacion); 

    public abstract void sePuedeAgregarEntidadBase() ;

    public abstract void puedeSerParteDeEntidadJuridica();
    
    public abstract String getNombreRegla();
    
    public long getId(){
    	return regla_id;
    }
    
    public String getTipo(){
    	return tipo;
    }
}
