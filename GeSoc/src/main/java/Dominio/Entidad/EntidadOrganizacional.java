package Dominio.Entidad;

import java.util.ArrayList;
import java.util.List;

import Dominio.Etiqueta;
import Dominio.Operacion;

public class EntidadOrganizacional {

	protected String nombreFicticio;
	protected CategoriaDeEntidad categoria;
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
		if(!categoria.puedeAgregarOperacion(this, operacion))
			throw new RuntimeException("Se supero el monto maximo");
			
		operaciones.add(operacion);
	}
	
	public List<Operacion> misOperaciones(){
		return operaciones;
	}
}	
