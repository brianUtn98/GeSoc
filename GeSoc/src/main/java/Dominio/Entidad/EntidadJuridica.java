package Dominio.Entidad;

import Dominio.DireccionPostal;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Transient;

@Entity
public class EntidadJuridica extends EntidadOrganizacional {

	private String razonSocial;
	private String cuit;

	@OneToOne
	private DireccionPostal direccionPostal;

	@Transient
	private TipoDeEntidad tipoDeEntidad;

	private String codigoInscripcionIGJ;

	public EntidadJuridica(){}

	public EntidadJuridica(String _nombreFicticio,String _razonSocial,String _cuit,DireccionPostal _direccionPostal,TipoDeEntidad _tipo,String _codigoInscripcionIGJ, CategoriaDeEntidad _categoria)
	{
		super(_nombreFicticio, _categoria);
		razonSocial =_razonSocial;
		cuit =_cuit;
		direccionPostal =_direccionPostal;
		tipoDeEntidad =_tipo;
		codigoInscripcionIGJ =_codigoInscripcionIGJ;
	}
	
	public EntidadJuridica(String _nombreFicticio,String _razonSocial,String _cuit,DireccionPostal _direccionPostal,String _codigoInscripcionIGJ){
		this(_nombreFicticio, _razonSocial, _cuit,_direccionPostal, null, _codigoInscripcionIGJ, null);
		
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

	public void setRazonSocial(String razonSocial) {
		// TODO Auto-generated method stub
		this.razonSocial = razonSocial;
	}

	public void setCuit(String cuit) {
		// TODO Auto-generated method stub
		this.cuit = cuit;
	}

	public void setCodigoInscripcionIGJ(String codigo) {
		// TODO Auto-generated method stub
		this.codigoInscripcionIGJ = codigo;
	}
}
