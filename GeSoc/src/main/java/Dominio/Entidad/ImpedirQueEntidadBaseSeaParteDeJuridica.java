package Dominio.Entidad;

import Dominio.Operacion;

public class ImpedirQueEntidadBaseSeaParteDeJuridica extends ReglaDeCategoria {
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
}
