package Dominio.Entidad;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import Dominio.Operacion;

@Entity

@DiscriminatorValue("BNE")
public class BloquearNuevosEgresos extends ReglaDeCategoria {

	public BloquearNuevosEgresos(){
		tipo= "BNE";
	}
	
    public BloquearNuevosEgresos(int montoMaximo) {
        super.montoMaximo = montoMaximo;
        tipo= "BNE";
    }

   
	@Override
	public void sePuedeAgregarEntidadBase() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void puedeSerParteDeEntidadJuridica() {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void sePuedeAgregarOperacion(EntidadOrganizacional entidad, Operacion operacion) {
		int montoTotal = entidad.misOperaciones().stream()
	    			.mapToInt(p -> p.getTotal().getMonto()).sum();
	    		  
	    	if( (montoTotal + operacion.getTotal().getMonto()) > montoMaximo)
	    		throw new ReglaDeCategoriaException("No se puede agregar un nuevo egreso");
	   
	}
	
	public String getNombreRegla() {
		return "Bloquear nuevo Egreso";
	};
}
