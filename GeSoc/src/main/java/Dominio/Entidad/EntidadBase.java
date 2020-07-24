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
		
		entidadJuridica = _entidadJuridica.filter(entidadJuridica -> entidadJuridica.puedeAgregarEntidadBase()); // Simplemente no agregamos la entidad juridica si no nos deja la regla
																													// La entidad base igual se crea. Esta bien esto?
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
