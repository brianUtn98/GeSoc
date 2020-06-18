package Dominio.Presupuesto;

import Dominio.Operacion;

import java.util.OptionalInt;

public class ValidacionPresupuestoMenorValor implements Validacion {
    private Operacion egreso;

    public ValidacionPresupuestoMenorValor(Operacion egreso) {
        this.egreso = egreso;
    }

    @Override
    public boolean validar() {
    	
    	if (egreso.getCriterioDeSeleccionMinimoValor()) {
	        // Faltaria chequear por si el criterio de seleccion es el del menor valor. Pero eso donde lo ponemos? (tanto el criterio en si, como la validacion)
	        OptionalInt costoMinimo = egreso.getPresupuestos().stream().mapToInt(Presupuesto::getTotal).min();
	        return costoMinimo.isPresent() && costoMinimo.getAsInt() == egreso.getPresupuestoSeleccionado().get().getTotal();
    	}
    	return true;
    }

    public Operacion getEgreso() {
        return egreso;
    }
}