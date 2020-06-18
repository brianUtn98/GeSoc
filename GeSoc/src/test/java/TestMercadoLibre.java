import Dominio.*;
import Ubicacion.Ciudad;
import Ubicacion.Moneda;
import Ubicacion.Pais;
import Ubicacion.Provincia;

import org.junit.Test;

import static org.junit.Assert.*;

public class TestMercadoLibre {

	@Test
	public void creacionCorrectaDePais() {
		ServicioInformacionUbicaciones servicio = new ServicioMercadoLibre("Argentina", "Capital Federal", "Capital Federal");
		Pais pais = servicio.getPais();
		
		assertTrue(pais.getNombre().equals("Argentina"));	
		assertEquals(-38.416096, pais.getInformacionGeografica().getLatitud(), 0.1);
	}
	
	@Test
	public void creacionCorrectaDeProvincia() {
		ServicioInformacionUbicaciones servicio = new ServicioMercadoLibre("Argentina", "Capital Federal", "Capital Federal");
		Provincia provincia = servicio.getProvincia();
		
		assertTrue(provincia.getNombre().equals("Capital Federal"));
		assertTrue(provincia.getZonaHoraria().equals("GMT-03:00"));
	}
	
	@Test
	public void creacionCorrectaDeCiudad() {
		ServicioInformacionUbicaciones servicio = new ServicioMercadoLibre("Argentina", "Capital Federal", "Capital Federal");
		Ciudad ciudad = servicio.getCiudad();
		
		assertTrue(ciudad.getNombre().equals("Capital Federal"));
		assertEquals(-34.6084175, ciudad.getInformacionGeografica().getLatitud(), 0.1);
	}
	
	@Test
	public void creacionCorrectaDeMoneda() {
		ServicioInformacionUbicaciones servicio = new ServicioMercadoLibre("Argentina", "Capital Federal", "Capital Federal");
		Moneda moneda = servicio.getMonedaLocal();
		
		assertTrue(moneda.getDescripcion().equals("Peso argentino"));
		assertTrue(moneda.getSimbolo().equals("$"));
	}	
}
