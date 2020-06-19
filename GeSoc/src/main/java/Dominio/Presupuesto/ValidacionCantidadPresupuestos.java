package Dominio.Presupuesto;

import Dominio.Operacion;

public class ValidacionCantidadPresupuestos implements Validacion {
    private Operacion egreso;
    private final Integer PRESUPUESTOS_REQUERIDOS = 2; // No se si esta bueno esto
    private String nombreValidacion;
    
    public ValidacionCantidadPresupuestos(Operacion egreso) {
        this.egreso = egreso;
        this.nombreValidacion = "Validacion de cantidad de presupuestos: ";
    }

    @Override
    public boolean validar() {
    	boolean resultadoValidacion = true;
        if(egreso.getRequierePresupuestos())
        	resultadoValidacion = egreso.getPresupuestos().size() >= PRESUPUESTOS_REQUERIDOS;
        	String mensaje = this.nombreValidacion+(resultadoValidacion?"OK":"Fallo");
        	egreso.notificar(mensaje);
        return resultadoValidacion;
    }

    public Operacion getEgreso() {
        return egreso;
    }
    
    public Integer getPRESUPUESTOS_REQUERIDOS() {
        return PRESUPUESTOS_REQUERIDOS;
    }
    
    public String getNombre() {
    	return nombreValidacion;
    }


}