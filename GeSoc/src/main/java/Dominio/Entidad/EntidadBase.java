package Dominio.Entidad;

import java.util.Optional;

public class EntidadBase implements EntidadOrganizacional {
	
	private String nombreFicticio;
	private String descripcion;
	private Optional<EntidadOrganizacional> entidadJuridica;
	
	public EntidadBase( String _nombreFicticio,String _descripcion)
	{
		this(_nombreFicticio, _descripcion, Optional.empty());
	}
	
	public EntidadBase( String _nombreFicticio,String _descripcion, Optional<EntidadOrganizacional> _entidadJuridica)
	{
		nombreFicticio =_nombreFicticio;
		descripcion = _descripcion;
		entidadJuridica = _entidadJuridica;
	}
	
	@Override
	public String getNombreFicticio()
	{
		return nombreFicticio;
	}
	
	public String getDescripcion()
	{
		return descripcion;
	}
	
	public Optional<EntidadOrganizacional> getentidadJuridica()
	{
		return entidadJuridica;
	}
}
