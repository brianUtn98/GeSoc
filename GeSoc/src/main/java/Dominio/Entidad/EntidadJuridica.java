package Dominio.Entidad;

import Dominio.DireccionPostal;

public class EntidadJuridica extends EntidadOrganizacional {

	private String razonSocial;
	private String cuit;
	private DireccionPostal direccionPostal;
	private TipoDeEntidad categoria;
	private String codigoInscripcionIGJ;
	
	public EntidadJuridica(String _nombreFicticio,String _razonSocial,String _cuit,DireccionPostal _direccionPostal,TipoDeEntidad _categoria,String _codigoInscripcionIGJ) 
	{
		super(_nombreFicticio);
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
	
	public DireccionPostal getDireccionPostal() {
		return direccionPostal;
	}
	public String getCodigoInscripcionIGJ() {
		return codigoInscripcionIGJ;
	}
	public String getCuit() {
		return cuit;
	}
	public TipoDeEntidad getCategoria() {
		return categoria;
	}
	
}
