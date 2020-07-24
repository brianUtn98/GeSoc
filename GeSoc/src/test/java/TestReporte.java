import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Dominio.Entidad.*;
import org.junit.Before;
import org.junit.Test;

import Dominio.DireccionPostal;
import Dominio.Etiqueta;
import Dominio.GeneradorDeReporte;
import Dominio.ItemOperacion;
import Dominio.Operacion;
import Dominio.Provedor;
import Dominio.Reporte;
import Dominio.TipoDocumento;
import Dominio.Pago.DineroEnCuenta;
import Dominio.Pago.MedioDePago;
import Dominio.Pago.TarjetaDeCredito;
import Dominio.Pago.ValorMonetario;
import Dominio.Ubicacion.Moneda;

public class TestReporte {

	EntidadOrganizacional entidad;
	Etiqueta et;
	Etiqueta et2;
	
 	@Before
    public void init(){
 		//creo una entidad
 		TipoDeEntidad tipo = new Empresa(TipoEmpresa.MedianaTramo1);
		CategoriaDeEntidad categoria = new CategoriaDeEntidad("ONG");
    	DireccionPostal direccion = new DireccionPostal("Calle falsa 123","Argentina","Capital Federal", "Capital Federal");
    	entidad = new EntidadJuridica("Pato feliz", "Patito S.A.", "30701258651", direccion, tipo, "jih5524", categoria);
    	//creo las etiquetas para esta entidad
    	et = new Etiqueta("gastos superfluos");
    	entidad.agregarEtiqueta(et);
    	et2 = new Etiqueta("gastos importantisimos");
    	entidad.agregarEtiqueta(et2);
    	
    	//agrego operaciones
    	Provedor prov = new Provedor("Homero", "Thompson", "Pato feliz", 29256328, new DireccionPostal("Calle falsa 123","Argentina","Capital Federal", "Capital Federal"), TipoDocumento.DNI);

		MedioDePago medio = new DineroEnCuenta(352265652);
		List<ItemOperacion> detalle = new ArrayList<>();
		Operacion op = new Operacion(29256328, prov , LocalDate.now(),  medio, detalle, null, true,true);
		op.addEtiqueta(et2);
		op.addEtiqueta(et);
    	
		//agrego detalle a la operacion
		List<ItemOperacion> detalleBarato = new ArrayList<>();
        detalleBarato.add(new ItemOperacion("Algo", new ValorMonetario(new Moneda("0", "ARS", "Peso", 2), 10)));
        detalleBarato.add(new ItemOperacion("Algo", new ValorMonetario(new Moneda("0", "ARS", "Peso", 2), 100)));
        op.setDetalle(detalleBarato);
 	
        //otra operacion
        MedioDePago medio2 = new TarjetaDeCredito();
        Operacion op2 = new Operacion(52564526, prov , LocalDate.now(),  medio2, detalle, null, true,true);
		op2.addEtiqueta(et2);
		
		List<ItemOperacion> detalleCaro = new ArrayList<>();
		detalleCaro.add(new ItemOperacion("otro", new ValorMonetario(new Moneda("0", "ARS", "Peso", 2), 9856)));
		detalleCaro.add(new ItemOperacion("nada", new ValorMonetario(new Moneda("0", "ARS", "Peso", 2), 10000)));
        op2.setDetalle(detalleCaro);
        
        entidad.agregarOperacion(op);
        entidad.agregarOperacion(op2);
 	}
	 
	@Test
    public void CalcularTotalDeReporte() {
		GeneradorDeReporte generador = new GeneradorDeReporte();
		generador.ingresarOperaciones(entidad.misOperaciones());
		generador.ingresarEtiqueta(et);
		
		Reporte report = generador.generarReporte();
		assertTrue(report.obtenerTotal().getMonto() == 110);
	}
	
	@Test
	public void CrearReporteNoVacio() {
		GeneradorDeReporte generador = new GeneradorDeReporte();
		generador.ingresarOperaciones(entidad.misOperaciones());
		generador.ingresarEtiqueta(et);
		generador.ingresarEtiqueta(et2);
		
		Reporte report = generador.generarReporte();
		assertFalse(report.devolverDetalle().entrySet().isEmpty());
	}
	
	@Test
	public void CrearReporteVacio() {
		GeneradorDeReporte generador = new GeneradorDeReporte();
		generador.ingresarEtiqueta(et);
		generador.ingresarEtiqueta(et2);
		
		Reporte report = generador.generarReporte();
		assertTrue(report.devolverDetalle().entrySet().isEmpty());
	}
	
	@Test
	public void ObtenerDetalleDeUnaCategoriaEspecifica() {
		GeneradorDeReporte generador = new GeneradorDeReporte();
		generador.ingresarOperaciones(entidad.misOperaciones());
		generador.ingresarEtiqueta(et);
		
		Reporte report = generador.generarReporte();
		assertTrue(report.devolverDetalle().entrySet().stream().anyMatch(e -> e.getKey() == et));
	}
	
	@Test
	public void ObtenerDetalleDeUnaCategoriaNoEspecificada() {
		GeneradorDeReporte generador = new GeneradorDeReporte();
		generador.ingresarOperaciones(entidad.misOperaciones());
		generador.ingresarEtiqueta(et);
		
		Reporte report = generador.generarReporte();
		assertFalse(report.devolverDetalle().entrySet().stream().anyMatch(e -> e.getKey() == et2));
	}
}
