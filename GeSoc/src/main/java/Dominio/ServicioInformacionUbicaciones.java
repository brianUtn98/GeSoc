package Dominio;

import Ubicacion.Ciudad;
import Ubicacion.Moneda;
import Ubicacion.Pais;
import Ubicacion.Provincia;

public interface ServicioInformacionUbicaciones {
	public Pais getPais();
	public Provincia getProvincia();
	public Ciudad getCiudad();
	public Moneda getMonedaLocal();
}
