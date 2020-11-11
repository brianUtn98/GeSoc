package Dominio.Mensajes;

import Dominio.Usuario.Usuario;
import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Embeddable
public class BandejaDeMensajes implements WithGlobalEntityManager {
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
