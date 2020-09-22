package Dominio.Mensajes;

import Dominio.Operacion;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Mensaje {
	@Id
	@GeneratedValue
	private long mensaje_id;

	@Transient
	private Operacion operacion;
	private String detalle;
	private boolean leido;
	
	public Mensaje(String _detalle, Operacion _operacion) {
		detalle = _detalle;
		operacion = _operacion;
		leido = false;
	}
	
	public void marcarLeido() {
		leido = true;
	}
	
	public String getDetalle() {
		return detalle;
	}
}
