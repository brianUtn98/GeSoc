import Dominio.*;
import Dominio.Pago.DineroEnCuenta;
import Dominio.Pago.MedioDePago;
import Dominio.Presupuesto.*;
import Dominio.Usuario.CreadorDeUsuario;
import Dominio.Usuario.TipoAdministrador;
import Dominio.Usuario.TipoEstandar;
import Dominio.Usuario.Usuario;
import Dominio.Usuario.ValidadorLongitud;
import Dominio.Usuario.ValidadorPassword;
import Dominio.Usuario.ValidadorSecuencial;
import junit.framework.*;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestBandejaMensajes {

    // Elementos comunes
    Provedor proveedorHomero;
    Provedor proveedorBart;
    MedioDePago medioDePago;
    Operacion operacionRequierePresupuesto; // La operacion sin presupuestos asignados
    Operacion operacionNoRequierePresupuesto; // La operacion sin presupuestos asignados
    Presupuesto presupuestoBarato;
    Presupuesto presupuestoCaro;
    Usuario usuarioRevisor;
    String mensajeFallo = "Validacion cantidad de presupuestos: Fallo";
    String mensajeOk = "Validacion cantidad de presupuestos: Fallo";
    
    @Before
    public void init(){
        proveedorHomero = new Provedor("Homero", "Thompson", "Pato feliz", 29256328,  new DireccionPostal("Calle falsa 123","Argentina","Capital Federal", "Capital Federal"), TipoDocumento.DNI);
        proveedorBart = new Provedor("Bart", "Thompson", "Pato infeliz", 29256329,  new DireccionPostal("Calle falsa 123","Argentina","Capital Federal", "Capital Federal"), TipoDocumento.DNI);

        medioDePago = new DineroEnCuenta(352265652);

        List<ItemOperacion> detalleBarato = new ArrayList<>();
        detalleBarato.add(new ItemOperacion("Algo", 10));
        List<ItemOperacion> detalleCaro = new ArrayList<>();
        detalleCaro.add(new ItemOperacion("Algo", 100));

        operacionRequierePresupuesto = new Operacion(29256328, proveedorHomero, LocalDate.now(),  medioDePago, detalleBarato, null, true, true);
        operacionNoRequierePresupuesto = new Operacion(29256328, proveedorHomero, LocalDate.now(),  medioDePago, detalleBarato, null, false, true);

        presupuestoBarato = new Presupuesto(proveedorHomero, detalleBarato);
        presupuestoCaro = new Presupuesto(proveedorBart, detalleCaro);
        
        CreadorDeUsuario builder = new CreadorDeUsuario("Esteban Quito");
        builder.setearTipoUsuario(new TipoAdministrador());
        List<ValidadorPassword> validadores = new ArrayList<ValidadorPassword>();
        ValidadorLongitud validadorLongitud = new ValidadorLongitud();
        ValidadorSecuencial validadorSecuencial = new ValidadorSecuencial();
        validadores.add(validadorLongitud);
        validadores.add(validadorSecuencial);
        builder.crearPassword("ajbh#1jvk",validadores);
        builder.setearTipoUsuario(new TipoAdministrador());

        usuarioRevisor = builder.crearUsuario();
    }

    @Test
    public void testNotificacionValidacionCantidadPresupuesto() {
        operacionRequierePresupuesto.addPresupusto(presupuestoBarato);
 
        operacionRequierePresupuesto.altaRevisor(usuarioRevisor);
        
        Validacion validacion = new ValidacionCantidadPresupuestos(operacionRequierePresupuesto);
        
        boolean resultado = validacion.validar();
        assertTrue(!(usuarioRevisor.verBandejaMensajes().isEmpty())); // Solo agregamos un presupuesto, pero se esperan 2
    }

    @Test
    public void testNotificacionValidacionCumplirPresupuesto() {
        operacionRequierePresupuesto.addPresupusto(presupuestoBarato);

        Validacion validacion = new ValidacionCumplirPresupuesto(operacionRequierePresupuesto);

        assertFalse(validacion.validar()); // No se selecciono un presupuesto para la compra
    }

    @Test
    public void testNotificacionValidacionPresupuestoMasBarato() {
        operacionRequierePresupuesto.addPresupusto(presupuestoBarato);
        operacionRequierePresupuesto.addPresupusto(presupuestoCaro);

        operacionRequierePresupuesto.setPresupuestoSeleccionado(presupuestoCaro);

        Validacion validacion = new ValidacionPresupuestoMenorValor(operacionRequierePresupuesto);

        assertFalse(validacion.validar()); // El presupuesto elegido es el caro
    }
//
//
//    @Test
//    public void testNoRequierePresupuesto() {
//        List<Validacion> validaciones = new ArrayList<>();
//        validaciones.add(new ValidacionCumplirPresupuesto(operacionNoRequierePresupuesto));
//        validaciones.add(new ValidacionCantidadPresupuestos(operacionNoRequierePresupuesto));
//
//        assertTrue(validaciones.stream().allMatch(validacion -> validacion.validar())); // Las validaciones que se hacen sobre compras que requieren presupuestos pasan cuando no se requiere uno
//    }
//
//    @Test
//    public void testPasanTodasLasValidaciones() {
//        operacionRequierePresupuesto.addPresupusto(presupuestoBarato);
//        operacionRequierePresupuesto.addPresupusto(presupuestoCaro);
//        operacionRequierePresupuesto.setPresupuestoSeleccionado(presupuestoBarato);
//
//        List<Validacion> validaciones = new ArrayList<>();
//        validaciones.add(new ValidacionCumplirPresupuesto(operacionRequierePresupuesto)); // Deberia pasar porque el presupuesto seleccionado es uno de los que tiene la compra asociados
//        validaciones.add(new ValidacionCantidadPresupuestos(operacionRequierePresupuesto)); // Deberia pasar porque tenemos 2 presupuestos (el minimo es 2)
//        validaciones.add(new ValidacionPresupuestoMenorValor(operacionRequierePresupuesto)); // Deberia pasar porque de los 2 presupuestos elegimos el mas barato
//
//        assertTrue(validaciones.stream().allMatch(validacion -> validacion.validar())); // Las validaciones que se hacen sobre compras que requieren presupuestos pasan cuando no se requiere uno
//    }

}
