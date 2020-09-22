package Dominio.Mensajes;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class BandejaDeMensajes {
	@Id
	@GeneratedValue
	private long baneja_id;

	@OneToMany
	@JoinColumn(name = "baneja_id")
	private List<Mensaje> mensajes;
	
	public BandejaDeMensajes(){
		mensajes = new ArrayList<Mensaje>();
	}
	
	public void agregarMensaje(Mensaje mensaje) {
		mensajes.add(mensaje);
	}

	public List<Mensaje> getMensajes() {
		// TODO Auto-generated method stub
		return mensajes;
	}
}
