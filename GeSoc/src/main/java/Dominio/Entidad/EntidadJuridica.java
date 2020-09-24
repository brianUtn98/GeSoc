package Dominio.Entidad;

import Dominio.DireccionPostal;

import javax.persistence.Entity;
import javax.persistence.Transient;

@Entity
public class EntidadJuridica extends EntidadOrganizacional {

	private String razonSocial;
	private String cuit;

	@Transient
	private DireccionPostal direccionPostal;

	@Transient
	private TipoDeEntidad tipoDeEntidad;

	private String codigoInscripcionIGJ;
	
	public EntidadJuridica(String _nombreFicticio,String _razonSocial,String _cuit,DireccionPostal _direccionPostal,TipoDeEntidad _tipo,String _codigoInscripcionIGJ, CategoriaDeEntidad _categoria)
	{
		super(_nombreFicticio, _categoria);
		razonSocial =_razonSocial;
		cuit =_cuit;
		direccionPostal =_direccionPostal;
		tipoDeEntidad =_tipo;
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
	public TipoDeEntidad getTipoDeEntidad() {
		return tipoDeEntidad;
	}

	public void puedeAgregarEntidadBase() {
		categoria.puedeAgregarEntidadBase();
	}
}
