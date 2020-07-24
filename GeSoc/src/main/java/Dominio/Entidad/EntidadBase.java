package Dominio.Entidad;

import java.util.Optional;

import javax.management.RuntimeErrorException;

public class EntidadBase extends EntidadOrganizacional {
	
	
	private String descripcion;
	private Optional<EntidadJuridica> entidadJuridica;
	
	public EntidadBase( String _nombreFicticio,String _descripcion, CategoriaDeEntidad _categoria)
	{
		this(_nombreFicticio, _descripcion, Optional.empty(), _categoria);
	}
	
	public EntidadBase( String _nombreFicticio,String _descripcion, Optional<EntidadJuridica> _entidadJuridica, CategoriaDeEntidad _categoria)
	{
		super(_nombreFicticio, _categoria);
		descripcion = _descripcion;

		if(!_categoria.puedeSerParteDeEntidadJuridica())
			throw new RuntimeException( "No puede ser parte de una entidad juridica");

		if(_entidadJuridica.isPresent() && _entidadJuridica.get().puedeAgregarEntidadBase())
			throw new RuntimeException( "La entidad juridica no admite entidades base");
		
		entidadJuridica = _entidadJuridica;
	}
	
	public String getDescripcion()
	{
		return descripcion;
	}
	
	public Optional<EntidadJuridica> getEntidadJuridica()
	{
		return entidadJuridica;
	}
}
