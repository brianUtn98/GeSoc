package Dominio.Usuario;

public class Usuario {
	private String nombre;
	private String hashPassword;
	private TipoDeUsuario tipoDeUsuario;
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



}
