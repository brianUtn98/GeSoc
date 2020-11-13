package Dominio;

import Dominio.Ubicacion.Ciudad;
import Dominio.Ubicacion.Pais;
import Dominio.Ubicacion.Provincia;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class DireccionPostal {
	@Id
	@GeneratedValue
	private long direccion_id;

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

	public DireccionPostal(){}

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

	public void setPais(String pais) {
		// TODO Auto-generated method stub
		this.pais = pais;
	}

	public void setProvincia(String provincia) {
		// TODO Auto-generated method stub
		this.provincia = provincia;
	}

	public void setCiudad(String ciudad) {
		// TODO Auto-generated method stub
		this.ciudad = ciudad;
	}
}
