package Dominio;

import Dominio.Ubicacion.Ciudad;
import Dominio.Ubicacion.Moneda;
import Dominio.Ubicacion.Pais;
import Dominio.Ubicacion.Provincia;

public interface ServicioInformacionUbicaciones {
	public Pais getPais();
	public Provincia getProvincia();
	public Ciudad getCiudad();
	public Moneda getMonedaLocal();
}
