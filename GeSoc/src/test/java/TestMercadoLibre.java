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
	@Test
	public void creacionProveedorUsandoServiceUbicacion() {
		ServicioInformacionUbicaciones servicio = new ServicioUbicacionesMock();
		//ServicioInformacionUbicaciones servicio = new ServicioMercadoLibre();
		
		String pais = servicio.getPaises().get(2);
		String provincia = servicio.getProvincias(pais).get(2);
		String ciudad = servicio.getCiudades(pais, provincia).get(3);
		
		DireccionPostal direccion = new DireccionPostal("Calle falsa 123", pais, provincia, ciudad);

		Provedor prov = new Provedor("Homero", "Thompson", "Pato feliz", 29256328, direccion, TipoDocumento.DNI);
		
		assertFalse(prov.getDireccionPostal().getPais().equals(""));
		assertNotNull(prov.getDireccionPostal().getPais());
	}
}
