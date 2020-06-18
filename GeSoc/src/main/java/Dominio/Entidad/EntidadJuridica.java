package Dominio.Entidad;

public class EntidadJuridica implements EntidadOrganizacional {

	private String nombreFicticio;
	private String razonSocial;
	private String cuit;
	private String direccionPostal;
	private CategoriaDeEntidad categoria;
	private String codigoInscripcionIGJ;
	
	public EntidadJuridica(String _nombreFicticio,String _razonSocial,String _cuit,String _direccionPostal,CategoriaDeEntidad _categoria,String _codigoInscripcionIGJ) 
	{
		nombreFicticio =_nombreFicticio;
		razonSocial =_razonSocial;
		cuit =_cuit;
		direccionPostal =_direccionPostal;
		categoria =_categoria;
		codigoInscripcionIGJ =_codigoInscripcionIGJ;
	}
	
	public String getRazonSocial() {
		return razonSocial;
	}
	
	@Override
	public String getNombreFicticio() {
		return nombreFicticio;
	}
	
	public String getDireccionPostal() {
		return direccionPostal;
	}
	public String getCodigoInscripcionIGJ() {
		return codigoInscripcionIGJ;
	}
	public String getCuit() {
		return cuit;
	}
	public CategoriaDeEntidad getCategoria() {
		return categoria;
	}
	
}
