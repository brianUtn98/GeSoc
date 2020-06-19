package Dominio.Presupuesto;

import Dominio.Operacion;

public class ValidacionCumplirPresupuesto implements Validacion {
    private Operacion egreso;
    private String nombreValidacion;
    
    public ValidacionCumplirPresupuesto(Operacion egreso) {
        this.egreso = egreso;
        this.nombreValidacion = "Validacion egreso tiene presupuesto: ";
    }

    @Override
    public boolean validar() {
    	boolean resultadoValidacion = true;
        if(egreso.getRequierePresupuestos())
        	resultadoValidacion = egreso.getPresupuestoSeleccionado().isPresent();
        	String mensaje = this.nombreValidacion+(resultadoValidacion?"OK":"Fallo");
        	egreso.notificar(mensaje);
        return resultadoValidacion;
    }

    public Operacion getEgreso() {
        return egreso;
    }
    
    public String getNombre() {
    	return nombreValidacion;
    }
}
