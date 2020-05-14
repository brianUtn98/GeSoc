package Dominio;

import java.util.Collections;
import java.util.List;

public class EntidadJuridica implements EntidadOrganizacional {

	public static final EntidadJuridica EMPTY = new EntidadJuridica("Entidad inexistente","",0,"",CategoriaDeEntidad.Pequenia,"");
	private String nombreFicticio;
	private String razonSocial;
	private Integer cuit;
	private String direccionPostal;
	private List<EntidadBase> entidadesBase;
	private CategoriaDeEntidad categoria;
	private String codigoInscripcionIGJ;
	
	public EntidadJuridica(String _nombreFicticio,String _razonSocial,Integer _cuit,String _direccionPostal,CategoriaDeEntidad _categoria,String _codigoInscripcionIGJ) 
	{
		nombreFicticio =_nombreFicticio;
		razonSocial =_razonSocial;
		cuit =_cuit;
		direccionPostal =_direccionPostal;
		categoria =_categoria;
		codigoInscripcionIGJ =_codigoInscripcionIGJ;
		entidadesBase = Collections.emptyList();
	}
	
	public String getRazonSocial() {
		return razonSocial;
	}
	public String getNombreFicticio() {
		return nombreFicticio;
	}
	public String getDireccionPostal() {
		return direccionPostal;
	}
	public String getCodigoInscripcionIGJ() {
		return codigoInscripcionIGJ;
	}
	public Integer getCuit() {
		return cuit;
	}
	public CategoriaDeEntidad getCategoria() {
		return categoria;
	}
	public List<EntidadBase> getEntidadesBase() {
		return entidadesBase;
	}
	public void setEntidadesBase(List<EntidadBase> _entidades) 
	{
		entidadesBase = _entidades;
	}
	
	
}
