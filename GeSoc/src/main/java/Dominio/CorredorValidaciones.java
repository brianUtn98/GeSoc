package Dominio;

import Dominio.Presupuesto.Validacion;
import Dominio.Presupuesto.ValidacionCantidadPresupuestos;
import Dominio.Presupuesto.ValidacionCumplirPresupuesto;
import Dominio.Presupuesto.ValidacionPresupuestoMenorValor;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CorredorValidaciones {
    private List<Validacion> validaciones;
    private List<Operacion> operacionesPendientes;

    private static CorredorValidaciones instance;
    public static CorredorValidaciones getInstance() {
        if (instance == null)
            instance = new CorredorValidaciones(Arrays.asList(
                    new ValidacionCumplirPresupuesto(),
                    new ValidacionCantidadPresupuestos(),
                    new ValidacionPresupuestoMenorValor()
            ));
        return instance;
    }

    private CorredorValidaciones(List<Validacion> validaciones) {
        this.validaciones = validaciones;
        operacionesPendientes = new ArrayList<>();
    }

    public void validar(Operacion operacion) {
        for(Validacion validacion : validaciones) {
            boolean resultado = validacion.validar(operacion);
            operacion.notificar(validacion.getNombre() + (resultado ? "OK" : "Falló"));
        }
    }

    public void validarPendientes() {
        operacionesPendientes.forEach(this::validar);
        operacionesPendientes = new ArrayList<>(); // Quizas esto es mejorable?
    }

    public void agregarOperacion(Operacion operacion) {
        operacionesPendientes.add(operacion);
    }
}
