package Dominio.Entidad;

import java.util.Optional;

public class EntidadBase extends EntidadOrganizacional {
	
	
	private String descripcion;
	private Optional<EntidadOrganizacional> entidadJuridica;
	
	public EntidadBase( String _nombreFicticio,String _descripcion)
	{
		this(_nombreFicticio, _descripcion, Optional.empty());
	}
	
	public EntidadBase( String _nombreFicticio,String _descripcion, Optional<EntidadOrganizacional> _entidadJuridica)
	{
		super(_nombreFicticio);
		descripcion = _descripcion;
		entidadJuridica = _entidadJuridica;
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
