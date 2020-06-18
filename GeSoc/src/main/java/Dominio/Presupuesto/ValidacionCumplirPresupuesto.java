package Dominio.Presupuesto;

import Dominio.Operacion;

public class ValidacionCumplirPresupuesto implements Validacion {
    private Operacion egreso;

    public ValidacionCumplirPresupuesto(Operacion egreso) {
        this.egreso = egreso;
    }

    @Override
    public boolean validar() {
        if(egreso.getRequierePresupuestos())
            return egreso.getPresupuestoSeleccionado().isPresent();
        return true;
    }

    public Operacion getEgreso() {
        return egreso;
    }
}
