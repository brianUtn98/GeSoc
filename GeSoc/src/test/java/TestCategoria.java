import Dominio.*;
import Dominio.Entidad.BloquearAgregarEntidadBaseAJuridica;
import Dominio.Entidad.BloquearNuevosEgresos;
import Dominio.Entidad.CategoriaDeEntidad;
import Dominio.Entidad.ImpedirQueEntidadBaseSeaParteDeJuridica;
import Dominio.Pago.DineroEnCuenta;
import Dominio.Pago.MedioDePago;
import Dominio.Pago.ValorMonetario;
import Dominio.Ubicacion.Moneda;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TestCategoria {
    @Test
    public void testBloquearEgresos() {
        CategoriaDeEntidad categoria = new CategoriaDeEntidad("ONG", Arrays.asList(new BloquearNuevosEgresos(50)));
        Provedor prov = new Provedor("Homero", "Thompson", "Pato feliz", 29256328, new DireccionPostal("Calle falsa 123","Argentina","Capital Federal", "Capital Federal"), TipoDocumento.DNI);

        MedioDePago medio = new DineroEnCuenta(352265652);
        List<ItemOperacion> detalle = new ArrayList<>();
        detalle.add(new ItemOperacion("Una Cosa", new ValorMonetario(new Moneda("0", "ARS", "Peso", 2), 100)));
        Operacion egreso = new Operacion(29256328, prov , LocalDate.now(),  medio, detalle, null, true,true);
        assertFalse(categoria.puedeAgregarOperacion(egreso));
    }

    @Test
    public void testPermitirEgresos() {
        CategoriaDeEntidad categoria = new CategoriaDeEntidad("ONG", Arrays.asList(new BloquearNuevosEgresos(150)));
        Provedor prov = new Provedor("Homero", "Thompson", "Pato feliz", 29256328, new DireccionPostal("Calle falsa 123","Argentina","Capital Federal", "Capital Federal"), TipoDocumento.DNI);

        MedioDePago medio = new DineroEnCuenta(352265652);
        List<ItemOperacion> detalle = new ArrayList<>();
        detalle.add(new ItemOperacion("Una Cosa", new ValorMonetario(new Moneda("0", "ARS", "Peso", 2), 100)));
        Operacion egreso = new Operacion(29256328, prov , LocalDate.now(),  medio, detalle, null, true,true);
        assertTrue(categoria.puedeAgregarOperacion(egreso));
    }

    @Test
    public void testBloquearAgregarEntidadBase() {
        CategoriaDeEntidad categoria = new CategoriaDeEntidad("ONG", Arrays.asList(new BloquearAgregarEntidadBaseAJuridica()));
        assertFalse(categoria.puedeAgregarEntidadBase());
    }

    @Test
    public void testBloquearSerParteDeJuridica() {
        CategoriaDeEntidad categoria = new CategoriaDeEntidad("ONG", Arrays.asList(new ImpedirQueEntidadBaseSeaParteDeJuridica()));
        assertFalse(categoria.puedeSerParteDeEntidadJuridica());
    }

}