package Dominio;

import java.util.List;

import Dominio.Ubicacion.Ciudad;
import Dominio.Ubicacion.Moneda;
import Dominio.Ubicacion.Pais;
import Dominio.Ubicacion.Provincia;

public interface ServicioInformacionUbicaciones {
	public List<String> getPaises();
	public List<String> getProvincias(String pais);
	public List<String> getCiudades(String pais, String provincia);	
	public List<String> getMonedas();
	
	//Estos metodos de abajo serian en caso de que necesite obtener
	//informacion extra a parte del nombre del pais
	public Pais getPais(String pais);
	public Provincia getProvincia(String pais, String provincia);
	public Ciudad getCiudad(String pais, String provincia, String ciudad);
	public Moneda getMonedaLocal(String pais);
}
