package Dominio;

import Dominio.Ubicacion.Ciudad;
import Dominio.Ubicacion.Pais;
import Dominio.Ubicacion.Provincia;

public class DireccionPostal {
	private String direccion;
	private String pais;
	private String provincia;
	private String ciudad;
	
	public DireccionPostal(String direccion, String pais, String provincia, String ciudad) {
		this.direccion = direccion;		
		this.pais = pais;
		this.provincia = provincia;
		this.ciudad = ciudad;
	}
	
	public String getPais() {
		return this.pais;
	}

	public String getProvincia() {
		return this.provincia;
	}
	
	public String getCiudad() {
		return this.ciudad;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
}
