package Dominio.Presupuesto;

import Dominio.Operacion;

public interface Validacion {
    public boolean validar(Operacion egreso);
    
    public String getNombre();
}

