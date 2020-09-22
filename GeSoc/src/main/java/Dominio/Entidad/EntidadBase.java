package Dominio.Entidad;

import javax.persistence.*;
import java.util.Optional;

@Entity
public class EntidadBase extends EntidadOrganizacional {
	
	private String descripcion;

	
	@OneToOne
	@JoinColumn(name="entidad_id", referencedColumnName="entidad_id", nullable=true)
	private EntidadJuridica entidadJuridica;
	
	public EntidadBase( String _nombreFicticio,String _descripcion, CategoriaDeEntidad _categoria)
	{
		this(_nombreFicticio, _descripcion, null, _categoria);
	}
	
	public EntidadBase( String _nombreFicticio,String _descripcion, EntidadJuridica _entidadJuridica, CategoriaDeEntidad _categoria)
	{
		super(_nombreFicticio, _categoria);
		descripcion = _descripcion;

		if(!_categoria.puedeSerParteDeEntidadJuridica())
			throw new RuntimeException( "No puede ser parte de una entidad juridica");

		if(_entidadJuridica != null && _entidadJuridica.puedeAgregarEntidadBase())
			throw new RuntimeException( "La entidad juridica no admite entidades base");
		
		entidadJuridica = _entidadJuridica;
	}
	
	public String getDescripcion()
	{
		return descripcion;
	}
	
	public Optional<EntidadJuridica> getEntidadJuridica()
	{
		return Optional.ofNullable(entidadJuridica);
	}
}
