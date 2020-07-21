import Dominio.*;
import Dominio.Pago.DineroEnCuenta;
import Dominio.Pago.MedioDePago;
import Dominio.Pago.ValorMonetario;
import Dominio.Presupuesto.*;
import Dominio.Ubicacion.Moneda;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestValidaciones {

    // Elementos comunes
    Provedor proveedorHomero;
    Provedor proveedorBart;
    MedioDePago medioDePago;
    Operacion operacionRequierePresupuesto; // La operacion sin presupuestos asignados
    Operacion operacionNoRequierePresupuesto; // La operacion sin presupuestos asignados
    Presupuesto presupuestoBarato;
    Presupuesto presupuestoCaro;

    @Before
    public void init(){
        proveedorHomero = new Provedor("Homero", "Thompson", "Pato feliz", 29256328,  new DireccionPostal("Calle falsa 123","Argentina","Capital Federal", "Capital Federal"), TipoDocumento.DNI);
        proveedorBart = new Provedor("Bart", "Thompson", "Pato infeliz", 29256329,  new DireccionPostal("Calle falsa 123","Argentina","Capital Federal", "Capital Federal"), TipoDocumento.DNI);

        medioDePago = new DineroEnCuenta(352265652);

        List<ItemOperacion> detalleBarato = new ArrayList<>();
        detalleBarato.add(new ItemOperacion("Algo", new ValorMonetario(new Moneda("0", "ARS", "Peso", 2), 10)));
        List<ItemOperacion> detalleCaro = new ArrayList<>();
        detalleCaro.add(new ItemOperacion("Algo", new ValorMonetario(new Moneda("0", "ARS", "Peso", 2), 100)));

        operacionRequierePresupuesto = new Operacion(29256328, proveedorHomero, LocalDate.now(),  medioDePago, detalleBarato, null, true, true);
        operacionNoRequierePresupuesto = new Operacion(29256328, proveedorHomero, LocalDate.now(),  medioDePago, detalleBarato, null, false, true);

        presupuestoBarato = new Presupuesto(proveedorHomero, detalleBarato);
        presupuestoCaro = new Presupuesto(proveedorBart, detalleCaro);
    }

    @Test
    public void testFaltanPresupuestos() {
        operacionRequierePresupuesto.addPresupusto(presupuestoBarato);

        Validacion validacion = new ValidacionCantidadPresupuestos();

        assertFalse(validacion.validar(operacionRequierePresupuesto)); // Solo agregamos un presupuesto, pero se esperan 2
    }

    @Test
    public void testNoSeCumplePresupuesto() {
        operacionRequierePresupuesto.addPresupusto(presupuestoBarato);

        Validacion validacion = new ValidacionCumplirPresupuesto();

        assertFalse(validacion.validar(operacionRequierePresupuesto)); // No se selecciono un presupuesto para la compra
    }

    @Test
    public void testNoEsPresupuestoMasBarato() {
        operacionRequierePresupuesto.addPresupusto(presupuestoBarato);
        operacionRequierePresupuesto.addPresupusto(presupuestoCaro);

        operacionRequierePresupuesto.setPresupuestoSeleccionado(presupuestoCaro);

        Validacion validacion = new ValidacionPresupuestoMenorValor();

        assertFalse(validacion.validar(operacionRequierePresupuesto)); // El presupuesto elegido es el caro
    }

    @Test
    public void testNoRequierePresupuesto() {
        List<Validacion> validaciones = new ArrayList<>();
        validaciones.add(new ValidacionCumplirPresupuesto());
        validaciones.add(new ValidacionCantidadPresupuestos());

        assertTrue(validaciones.stream().allMatch(validacion -> validacion.validar(operacionNoRequierePresupuesto))); // Las validaciones que se hacen sobre compras que requieren presupuestos pasan cuando no se requiere uno
    }

    @Test
    public void testPasanTodasLasValidaciones() {
        operacionRequierePresupuesto.addPresupusto(presupuestoBarato);
        operacionRequierePresupuesto.addPresupusto(presupuestoCaro);
        operacionRequierePresupuesto.setPresupuestoSeleccionado(presupuestoBarato);

        List<Validacion> validaciones = new ArrayList<>();
        validaciones.add(new ValidacionCumplirPresupuesto()); // Deberia pasar porque el presupuesto seleccionado es uno de los que tiene la compra asociados
        validaciones.add(new ValidacionCantidadPresupuestos()); // Deberia pasar porque tenemos 2 presupuestos (el minimo es 2)
        validaciones.add(new ValidacionPresupuestoMenorValor()); // Deberia pasar porque de los 2 presupuestos elegimos el mas barato

        assertTrue(validaciones.stream().allMatch(validacion -> validacion.validar(operacionRequierePresupuesto))); // Las validaciones que se hacen sobre compras que requieren presupuestos pasan cuando no se requiere uno
    }
}
