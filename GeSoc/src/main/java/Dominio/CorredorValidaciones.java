package Dominio;

import Dominio.Presupuesto.Validacion;
import Dominio.Presupuesto.ValidacionCantidadPresupuestos;
import Dominio.Presupuesto.ValidacionCumplirPresupuesto;
import Dominio.Presupuesto.ValidacionPresupuestoMenorValor;
import Dominio.Usuario.Usuario;
import org.uqbarproject.jpa.java8.extras.EntityManagerOps;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class CorredorValidaciones implements WithGlobalEntityManager, EntityManagerOps, TransactionalOps {
    private List<Validacion> validaciones;
    private List<Operacion> operacionesPendientes;
    private List<Operacion> operacionesValidadas;

    private static CorredorValidaciones instance;
    public static CorredorValidaciones getInstance() {
        if (instance == null)
            instance = new CorredorValidaciones(Arrays.asList(
                    new ValidacionCumplirPresupuesto(),
                    new ValidacionCantidadPresupuestos(),
                    new ValidacionPresupuestoMenorValor()
            ));
        instance.cargar();
        return instance;
    }

    private void cargar() {
        operacionesValidadas = entityManager()
                .createQuery("from OperacionValidada", OperacionValidada.class)
                .getResultList()
                .stream().map(operacionValidada -> operacionValidada.getOperacion())
                .collect(Collectors.toList());

        operacionesPendientes = entityManager()
                .createQuery("from Operacion", Operacion.class)
                .getResultList()
                .stream().filter(operacion -> !operacionesValidadas.stream().anyMatch(unaOperacion -> unaOperacion.getId() != operacion.getId()))
                .collect(Collectors.toList());
    }

    private CorredorValidaciones(List<Validacion> validaciones) {
        this.validaciones = validaciones;
        operacionesPendientes = new ArrayList<>();
        operacionesValidadas = new ArrayList<>();
    }

    public void validar(Operacion operacion) {
        withTransaction(() -> {
            for(Validacion validacion : validaciones) {
                    boolean resultado = validacion.validar(operacion);
                    operacion.notificar(validacion.getNombre() + (resultado ? "OK" : "Falló"));
            }
            persist(new OperacionValidada(operacion));
            operacionesValidadas.add(operacion);
        });
    }

    public void validarPendientes() {
        operacionesPendientes.forEach(this::validar);
        operacionesPendientes = new ArrayList<>(); // Quizas esto es mejorable?
    }

    public void agregarOperacion(Operacion operacion) {
        operacionesPendientes.add(operacion);
    }

    public List<Operacion> getOperacionesPendientes() {
        return operacionesPendientes;
    }

    public List<Operacion> getOperacionesValidadas() {
        return operacionesValidadas;
    }
}
