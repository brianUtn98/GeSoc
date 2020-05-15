package Dominio;

public class Usuario {
	private String nombre;
	private String password;
	private Boolean esAdministrador;
	
	public Usuario(String _nombre, String _password, Boolean _esAdministrador)
	{
		nombre =_nombre;
		password = _password;
		esAdministrador = _esAdministrador;
	}

	public String getNombre() {
		return nombre;
	}

	public String getPassword() {
		return password;
	}

	public Boolean getEsAdministrador() {
		return esAdministrador;
	}
	
	public void setAdministrador(Boolean _esAdministrador) {
		esAdministrador = _esAdministrador;
	}
	
	public void setPassword(String _password) {
		password = _password;
	}
}
