package Dominio;

public class Provedor {
	private String nombre;
	private String apellido;
	private String razonSocial;
	private Integer numeroDocumento;
	private String direccionPostal;
	private TipoDocumento tipoDocumento;
	
	public Provedor(String nombre, String apellido, String razonSocial, Integer numeroDocumento,
			String direccionPostal, TipoDocumento tipoDocumento) {
		this.nombre = nombre;
		this.apellido = apellido;
		this.razonSocial = razonSocial;
		this.numeroDocumento = numeroDocumento;
		this.direccionPostal = direccionPostal;
		this.tipoDocumento = tipoDocumento;
	}
	
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getRazonSocial() {
		return razonSocial;
	}
	public void setRazonSocial(String razonSocial) {
		this.razonSocial = razonSocial;
	}
	public Integer getNumeroDocumento() {
		return numeroDocumento;
	}
	public void setNumeroDocumento(Integer numeroDocumento) {
		this.numeroDocumento = numeroDocumento;
	}
	public String getDireccionPostal() {
		return direccionPostal;
	}
	public void setDireccionPostal(String direccionPostal) {
		this.direccionPostal = direccionPostal;
	}
	
	public TipoDocumento getTipoDocumento() {
		return this.tipoDocumento;
	}
	
	public void setTipoDocumento(TipoDocumento tipoDocumento) {
		this.tipoDocumento = tipoDocumento;
	}
	

}
