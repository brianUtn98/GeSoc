package Dominio.Entidad;

import Dominio.Operacion;

public abstract class ReglaDeCategoria {
	
	protected int montoMaximo;
	
    public abstract void sePuedeAgregarOperacion(EntidadOrganizacional entidad,Operacion operacion); 

    public abstract void sePuedeAgregarEntidadBase() ;

    public abstract void puedeSerParteDeEntidadJuridica();
}
