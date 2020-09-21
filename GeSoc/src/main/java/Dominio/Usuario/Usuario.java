package Dominio.Usuario;

import java.util.ArrayList;
import java.util.List;

import Dominio.Operacion;
import Dominio.Mensajes.BandejaDeMensajes;
import Dominio.Mensajes.Mensaje;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity

public class Usuario{
	@Id
	@GeneratedValue
	private long id;

	private String nombre;
	@Transient
	private String hashPassword;
	@Transient
	private TipoDeUsuario tipoDeUsuario;
	@Transient
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
