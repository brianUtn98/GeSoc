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

		_categoria.puedeSerParteDeEntidadJuridica();

		if(_entidadJuridica != null)
			_entidadJuridica.puedeAgregarEntidadBase();
			
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
