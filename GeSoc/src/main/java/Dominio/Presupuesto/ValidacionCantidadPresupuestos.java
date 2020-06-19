package Dominio.Presupuesto;

import Dominio.Operacion;

public class ValidacionCantidadPresupuestos implements Validacion {
    private Operacion egreso;
    private final Integer PRESUPUESTOS_REQUERIDOS = 2; // No se si esta bueno esto

    public ValidacionCantidadPresupuestos(Operacion egreso) {
        this.egreso = egreso;
    }

    @Override
    public boolean validar() {
        if(egreso.getRequierePresupuestos())
            return egreso.getPresupuestos().size() >= PRESUPUESTOS_REQUERIDOS;
        return true;
    }

    public Operacion getEgreso() {
        return egreso;
    }

    public Integer getPRESUPUESTOS_REQUERIDOS() {
        return PRESUPUESTOS_REQUERIDOS;
    }
}