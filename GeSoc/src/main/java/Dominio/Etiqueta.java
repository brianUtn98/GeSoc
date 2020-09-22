package Dominio;

import Dominio.Entidad.EntidadOrganizacional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Etiqueta {
	@Id
	@GeneratedValue
	private long etiqueta_id;

	private String nombre;
	
	public Etiqueta(String _nombre) {
		nombre = _nombre;
	}
}
