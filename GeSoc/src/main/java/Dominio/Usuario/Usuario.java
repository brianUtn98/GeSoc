package Dominio.Usuario;

import java.util.ArrayList;
import java.util.List;

import Dominio.Operacion;
import Dominio.Mensajes.BandejaDeMensajes;
import Dominio.Mensajes.Mensaje;

public class Usuario{
	private String nombre;
	private String hashPassword;
	private TipoDeUsuario tipoDeUsuario;
	private BandejaDeMensajes bandejaDeMensajes;
	
	public Usuario(String _nombre, String _hashPassword, TipoDeUsuario _tipo, BandejaDeMensajes _bandeja)
	{
		nombre =_nombre;
		hashPassword = _hashPassword;
		tipoDeUsuario = _tipo;
		bandejaDeMensajes = _bandeja;
	}

	public String getNombre() {
		return nombre;
	}

	public String getHashPassword() {
		return hashPassword;
	}

	/*public void setPassword(String _password) {
		password = _password;
	} Lo comento porque quizás a futuro queramos cambiar la pass, pero por ahora lo dejamos en manos del builder*/
	
	public List<Mensaje> verBandejaMensajes() {
		return bandejaDeMensajes.getMensajes();
	}

	public void updateBandeja(Mensaje mensaje) {
		// TODO Auto-generated method stub
		bandejaDeMensajes.agregarMensaje(mensaje);
	}


}
