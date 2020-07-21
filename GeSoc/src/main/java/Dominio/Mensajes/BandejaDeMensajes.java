package Dominio.Mensajes;

import java.util.ArrayList;
import java.util.List;

public class BandejaDeMensajes {
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
