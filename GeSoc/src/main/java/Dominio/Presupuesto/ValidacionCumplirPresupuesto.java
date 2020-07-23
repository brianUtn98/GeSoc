package Dominio.Presupuesto;

import Dominio.Operacion;

public class ValidacionCumplirPresupuesto implements Validacion {
    private String nombreValidacion;
    
    public ValidacionCumplirPresupuesto() {
        this.nombreValidacion = "Validacion egreso tiene presupuesto: ";
    }

    @Override
    public boolean validar(Operacion egreso) {
    	boolean resultadoValidacion = true;
        if(egreso.getRequierePresupuestos())
        	resultadoValidacion = egreso.getPresupuestoSeleccionado().isPresent();
        return resultadoValidacion;
    }
    
    public String getNombre() {
    	return nombreValidacion;
    }
}
