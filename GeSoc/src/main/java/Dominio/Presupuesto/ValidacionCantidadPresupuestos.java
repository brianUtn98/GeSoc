package Dominio.Presupuesto;

import Dominio.Operacion;

public class ValidacionCantidadPresupuestos implements Validacion {
    private final Integer PRESUPUESTOS_REQUERIDOS = 2; // No se si esta bueno esto
    private String nombreValidacion;
    
    public ValidacionCantidadPresupuestos() {
        this.nombreValidacion = "Validacion de cantidad de presupuestos: ";
    }

    @Override
    public boolean validar(Operacion egreso) {
    	boolean resultadoValidacion = true;
        if(egreso.getRequierePresupuestos())
        	resultadoValidacion = egreso.getPresupuestos().size() >= PRESUPUESTOS_REQUERIDOS;        	
        return resultadoValidacion;
    }

    public Integer getPRESUPUESTOS_REQUERIDOS() {
        return PRESUPUESTOS_REQUERIDOS;
    }
    
    public String getNombre() {
    	return nombreValidacion;
    }
}