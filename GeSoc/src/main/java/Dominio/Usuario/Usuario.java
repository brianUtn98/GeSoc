package Dominio.Usuario;

import java.util.ArrayList;
import java.util.List;

import Dominio.Operacion;
import Dominio.Mensajes.BandejaDeMensajes;
import Dominio.Mensajes.Mensaje;

import javax.persistence.*;

@Entity
@Table(name="usuarios")
public class Usuario{
	@Id
	@GeneratedValue
	private long usuario_id;

	private String nombre;
	private String hashPassword;

	//TODO qué hacemos con estos tipos de usuario?
	@ManyToOne
	private TipoDeUsuario tipoDeUsuario;

	@Embedded
	private BandejaDeMensajes bandejaDeMensajes;
	
	public Usuario(String _nombre, String _hashPassword, TipoDeUsuario _tipo, BandejaDeMensajes _bandeja)
	{
		nombre =_nombre;
		hashPassword = _hashPassword;
		tipoDeUsuario = _tipo;
		bandejaDeMensajes = _bandeja;
	}

	public Usuario() {}

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

	public long getId() {
		return usuario_id;
	}


}
