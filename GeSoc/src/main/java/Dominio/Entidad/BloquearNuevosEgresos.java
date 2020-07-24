package Dominio.Entidad;

import Dominio.Operacion;

public class BloquearNuevosEgresos extends ReglaDeCategoria {
    private int montoMaximo; // Este monto va aca o en la entidad???

    public BloquearNuevosEgresos(int montoMaximo) {
        this.montoMaximo = montoMaximo;
    }

    @Override
    public boolean sePuedeAgregarOperacion(Operacion operacion) {
        return operacion.getTotal().getMonto() < montoMaximo;
    }
}
