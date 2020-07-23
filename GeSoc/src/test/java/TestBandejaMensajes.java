import Dominio.*;
import Dominio.Mensajes.BandejaDeMensajes;
import Dominio.Pago.DineroEnCuenta;
import Dominio.Pago.MedioDePago;
import Dominio.Pago.ValorMonetario;
import Dominio.Presupuesto.*;
import Dominio.Ubicacion.Moneda;
import Dominio.Usuario.CreadorDeUsuario;
import Dominio.Usuario.TipoAdministrador;
import Dominio.Usuario.Usuario;
import Dominio.Usuario.ValidadorLongitud;
import Dominio.Usuario.ValidadorPassword;
import Dominio.Usuario.ValidadorSecuencial;


import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

public class TestBandejaMensajes {

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
        detalleBarato.add(new ItemOperacion("Algo", new ValorMonetario(new Moneda("0", "ARS", "Peso", 2), 10)));
        List<ItemOperacion> detalleCaro = new ArrayList<>();
        detalleCaro.add(new ItemOperacion("Algo", new ValorMonetario(new Moneda("0", "ARS", "Peso", 2), 100)));

        operacionRequierePresupuesto = new Operacion(29256328, proveedorHomero, LocalDate.now(),  medioDePago, detalleBarato, null, true, true);
        operacionNoRequierePresupuesto = new Operacion(29256328, proveedorHomero, LocalDate.now(),  medioDePago, detalleBarato, null, false, true);

        presupuestoBarato = new Presupuesto(proveedorHomero, detalleBarato);
        presupuestoCaro = new Presupuesto(proveedorBart, detalleCaro);
        
        CreadorDeUsuario builder = new CreadorDeUsuario("Esteban Quito");
        BandejaDeMensajes bandeja = new BandejaDeMensajes();
        builder.setearBandejaDeMensajse(bandeja);
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
    public void testNotificacionValidacionCantidadPresupuestoDistinta() {
        operacionRequierePresupuesto.addPresupusto(presupuestoBarato);
 
        operacionRequierePresupuesto.altaRevisor(usuarioRevisor);
        
        Validacion validacion = new ValidacionCantidadPresupuestos();
        String mensaje = validacion.getNombre()+(validacion.validar(operacionRequierePresupuesto)?"OK":"Fallo");
        operacionRequierePresupuesto.notificar(mensaje); 
        
      //Se notifica al usuario que la cantidad de presupuesto no es la correcta
        Assert.assertEquals("Validacion de cantidad de presupuestos: Fallo",usuarioRevisor.verBandejaMensajes().get(0).getDetalle());
    }

    @Test
    public void testNotificacionValidacionCantidadPresupuestoIgual() {
        operacionRequierePresupuesto.addPresupusto(presupuestoBarato);
        operacionRequierePresupuesto.addPresupusto(presupuestoCaro);
        
        operacionRequierePresupuesto.altaRevisor(usuarioRevisor);
        
        Validacion validacion = new ValidacionCantidadPresupuestos();
        String mensaje = validacion.getNombre()+(validacion.validar(operacionRequierePresupuesto)?"OK":"Fallo");
        operacionRequierePresupuesto.notificar(mensaje);
        
        //Se notifica al usuario que la cantidad de presupuesto es la correcta 
        Assert.assertEquals("Validacion de cantidad de presupuestos: OK",usuarioRevisor.verBandejaMensajes().get(0).getDetalle());
    }
    
    
    @Test
    public void testNotificacionValidacionNoCumplePresupuesto() {
        operacionRequierePresupuesto.addPresupusto(presupuestoBarato);

        operacionRequierePresupuesto.altaRevisor(usuarioRevisor);
        
        Validacion validacion = new ValidacionCumplirPresupuesto();
        String mensaje = validacion.getNombre()+(validacion.validar(operacionRequierePresupuesto)?"OK":"Fallo");
        operacionRequierePresupuesto.notificar(mensaje); 
        
        //  Se notifica al usuario que no se selecciono un presupuesto para la compra
        Assert.assertEquals("Validacion egreso tiene presupuesto: Fallo",usuarioRevisor.verBandejaMensajes().get(0).getDetalle());
    }

    @Test
    public void testNotificacionValidacionCumplePresupuesto() {
        operacionRequierePresupuesto.addPresupusto(presupuestoBarato);
        
        operacionRequierePresupuesto.setPresupuestoSeleccionado(presupuestoBarato);
        
        operacionRequierePresupuesto.altaRevisor(usuarioRevisor);
        
        Validacion validacion = new ValidacionCumplirPresupuesto();
        String mensaje = validacion.getNombre()+(validacion.validar(operacionRequierePresupuesto)?"OK":"Fallo");
        operacionRequierePresupuesto.notificar(mensaje);
        
        // Se notifica al usuario que se selecciono un presupuesto para la compra
        Assert.assertEquals("Validacion egreso tiene presupuesto: OK",usuarioRevisor.verBandejaMensajes().get(0).getDetalle());
    }

    
    @Test
    public void testNotificacionValidacionPresupuestoMasCaro() {
        operacionRequierePresupuesto.addPresupusto(presupuestoBarato);
        operacionRequierePresupuesto.addPresupusto(presupuestoCaro);

        operacionRequierePresupuesto.setPresupuestoSeleccionado(presupuestoCaro);
        operacionRequierePresupuesto.altaRevisor(usuarioRevisor);
        
        Validacion validacion = new ValidacionPresupuestoMenorValor();
        String mensaje = validacion.getNombre()+(validacion.validar(operacionRequierePresupuesto)?"OK":"Fallo");
        operacionRequierePresupuesto.notificar(mensaje);

        // Se notifica al usuario que el presupuesto elegido es el caro
        Assert.assertEquals("Validacion de seleccion de presupuesto de menor valor: Fallo"
        		,usuarioRevisor.verBandejaMensajes().get(0).getDetalle());
    }

    @Test
    public void testNotificacionValidacionPresupuestoMasBarato() {
        operacionRequierePresupuesto.addPresupusto(presupuestoBarato);
        operacionRequierePresupuesto.addPresupusto(presupuestoCaro);

        operacionRequierePresupuesto.setPresupuestoSeleccionado(presupuestoBarato);
        operacionRequierePresupuesto.altaRevisor(usuarioRevisor);
        
        Validacion validacion = new ValidacionPresupuestoMenorValor();
        String mensaje = validacion.getNombre()+(validacion.validar(operacionRequierePresupuesto)?"OK":"Fallo");
        operacionRequierePresupuesto.notificar(mensaje);

        
        // Se notifica al usuario que el presupuesto elegido es el barato
        Assert.assertEquals("Validacion de seleccion de presupuesto de menor valor: OK"
        		,usuarioRevisor.verBandejaMensajes().get(0).getDetalle());
    }    
}
