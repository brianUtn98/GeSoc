import Dominio.*;
import Dominio.Ubicacion.Ciudad;
import Dominio.Ubicacion.Moneda;
import Dominio.Ubicacion.Pais;
import Dominio.Ubicacion.Provincia;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestMercadoLibre {
	@Test
	public void obtencionListaDePaises() {
		ServicioInformacionUbicaciones servicio = new ServicioUbicacionesMock();
		
		assertTrue(servicio.getPaises().contains("Argentina"));
	}
	@Test
	public void obtencionListaProvincias() {
		ServicioInformacionUbicaciones servicio = new ServicioUbicacionesMock();
		
		assertTrue(servicio.getProvincias("Argentina").contains("Santa Fe"));
	}
	@Test
	public void obtencionListaCiudades() {
		ServicioInformacionUbicaciones servicio = new ServicioUbicacionesMock();
		
		assertTrue(servicio.getCiudades("Argentina", "Capital Federal").contains("Capital Federal"));
	}
	@Test
	public void obtencioMoneda() {
		ServicioInformacionUbicaciones servicio = new ServicioUbicacionesMock();
		
		assertTrue(servicio.getMonedaLocal("Argentina").getCodigo().equals("ARS"));
	}
}
