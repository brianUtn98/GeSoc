package Dominio.Usuario;

import java.util.ArrayList;
import java.util.List;

import Dominio.Operacion;

public class Usuario{
	private String nombre;
	private String hashPassword;
	private TipoDeUsuario tipoDeUsuario;
	private List<String> mensajes = new ArrayList<String>();
	
	public Usuario(String _nombre, String _hashPassword, TipoDeUsuario _tipo)
	{
		nombre =_nombre;
		hashPassword = _hashPassword;
		tipoDeUsuario = _tipo;
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
	
	public List<String> verBandejaMensajes() {
		return mensajes;
	}

	public void updateBandeja(String mensaje) {
		// TODO Auto-generated method stub
		mensajes.add(mensaje);
	}


}
