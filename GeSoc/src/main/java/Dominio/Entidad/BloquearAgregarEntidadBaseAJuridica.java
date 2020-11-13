package Dominio.Entidad;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import Dominio.Operacion;

@Entity

@DiscriminatorValue("BAE")
public class BloquearAgregarEntidadBaseAJuridica extends ReglaDeCategoria {
	
	public BloquearAgregarEntidadBaseAJuridica(){
		tipo= "BAE";
	}
	
    public void sePuedeAgregarEntidadBase()  {
    	throw new ReglaDeCategoriaException("No se puede agregar una Entidad Base a la Juridica");
    }

	@Override
	public void sePuedeAgregarOperacion(EntidadOrganizacional entidad, Operacion operacion) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void puedeSerParteDeEntidadJuridica() {
		// TODO Auto-generated method stub
		
	}
	
	public String getNombreRegla() {
		return "Bloquear agregar Entidad Base";
	};
}
