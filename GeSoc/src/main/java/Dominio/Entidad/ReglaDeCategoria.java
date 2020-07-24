package Dominio.Entidad;

import Dominio.Operacion;

public abstract class ReglaDeCategoria {
    public boolean sePuedeAgregarOperacion(EntidadOrganizacional entidad,Operacion operacion) {
        return true;
    }

    public boolean sePuedeAgregarEntidadBase() {
        return true;
    }

    public boolean puedeSerParteDeEntidadJuridica() {
        return true;
    }
}