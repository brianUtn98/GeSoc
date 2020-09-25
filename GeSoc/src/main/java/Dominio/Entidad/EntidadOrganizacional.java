package Dominio.Entidad;

import java.util.ArrayList;
import java.util.List;

import Dominio.Etiqueta;
import Dominio.Operacion;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class EntidadOrganizacional {
	@Id
	@GeneratedValue
	private long entidad_id;

	protected String nombreFicticio;

	@ManyToOne
	protected CategoriaDeEntidad categoria;

	//TODO es un ManyToMany?
	@OneToMany
	private List<Operacion> operaciones;
	
	public EntidadOrganizacional(String _nombreFicticio, CategoriaDeEntidad _categoria) {
		nombreFicticio =_nombreFicticio;
		operaciones = new ArrayList<Operacion>();
		categoria = _categoria;
	}
	
	
	public String getNombreFicticio()
	{
		return nombreFicticio;
	}
	
	public void setNombreFicticio( String _nombreFicticio) {
		
	}

	public CategoriaDeEntidad getCategoria() {
		return this.categoria;
	}
	
	public void setCategoria(CategoriaDeEntidad categoria) {
		this.categoria = categoria;
	}
	

	public void agregarOperacion(Operacion operacion) {
		categoria.puedeAgregarOperacion(this, operacion);
			
		operaciones.add(operacion);
	}
	
	public List<Operacion> misOperaciones(){
		return operaciones;
	}
}	
