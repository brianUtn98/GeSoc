package Dominio.Mensajes;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class BandejaDeMensajes {
	@OneToMany
	@JoinColumn(name = "usuario_id")
	private List<Mensaje> mensajes;
	
	public BandejaDeMensajes(){
		mensajes = new ArrayList<Mensaje>();
	}
	
	public void agregarMensaje(Mensaje mensaje) {
		mensajes.add(mensaje);
	}

	public List<Mensaje> getMensajes() {
		return mensajes;
	}
}
