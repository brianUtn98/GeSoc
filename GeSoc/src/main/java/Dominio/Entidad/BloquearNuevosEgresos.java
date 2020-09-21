package Dominio.Entidad;

import java.util.List;
import java.util.stream.Collectors;

import Dominio.Operacion;

public class BloquearNuevosEgresos extends ReglaDeCategoria {
    private int montoMaximo; // NO EN LA ENTIDAD!

    public BloquearNuevosEgresos(int montoMaximo) {
        this.montoMaximo = montoMaximo;
    }

    @Override
    public boolean sePuedeAgregarOperacion(EntidadOrganizacional entidad,Operacion operacion) {
    	int montoTotal = entidad.misOperaciones().stream()
    			.mapToInt(p -> p.getTotal().getMonto()).sum();
    		  
    	return (montoTotal + operacion.getTotal().getMonto()) < montoMaximo;
    }
}
