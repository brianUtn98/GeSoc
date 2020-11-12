package Dominio.Entidad;

import javax.persistence.DiscriminatorValue;

import Dominio.Operacion;

@DiscriminatorValue("BloquearAgregarEntidadBaseAJuridica")
public class BloquearAgregarEntidadBaseAJuridica extends ReglaDeCategoria {
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
}
