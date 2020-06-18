package Dominio;

import Dominio.Ubicacion.Ciudad;
import Dominio.Ubicacion.Pais;
import Dominio.Ubicacion.Provincia;

public class DireccionPostal {
	private String direccion;
	private Pais pais;
	private Provincia provincia;
	private Ciudad ciudad;
	
	public DireccionPostal(String direccion, String pais, String provincia, String ciudad) {
		this.setDireccion(direccion);
		ServicioMercadoLibre info = new ServicioMercadoLibre(pais, provincia, ciudad);
		
		this.pais = info.getPais();
		this.provincia = info.getProvincia();
		this.ciudad = info.getCiudad();
	}
	
	public Pais getPais() {
		return this.pais;
	}

	public Provincia getProvincia() {
		return this.provincia;
	}
	
	public Ciudad getCiudad() {
		return this.ciudad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
}
