package Dominio.Entidad;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import Dominio.Operacion;

@Entity
@DiscriminatorValue("IEB")
public class ImpedirQueEntidadBaseSeaParteDeJuridica extends ReglaDeCategoria {
    
	public ImpedirQueEntidadBaseSeaParteDeJuridica() {
		tipo = "IEB";
	}
	
	public void puedeSerParteDeEntidadJuridica() {
    	throw new ReglaDeCategoriaException("Entidad Base no puede ser parte de la entidad Jurídica");
    }

	@Override
	public void sePuedeAgregarOperacion(EntidadOrganizacional entidad, Operacion operacion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void sePuedeAgregarEntidadBase() {
		// TODO Auto-generated method stub
		
	}
	
	public String getNombreRegla() {
		return "Impedir que Entidad Base sea parte de Jurídica";
	};
}
