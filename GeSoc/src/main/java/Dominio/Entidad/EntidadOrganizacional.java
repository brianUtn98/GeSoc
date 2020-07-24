package Dominio.Entidad;

import java.util.ArrayList;
import java.util.List;

import Dominio.Etiqueta;
import Dominio.Operacion;

public class EntidadOrganizacional {

	protected String nombreFicticio;
	protected CategoriaDeEntidad categoria;
	private List<Operacion> operaciones;
	private List<Etiqueta> etiquetas;
	
	public EntidadOrganizacional(String _nombreFicticio, CategoriaDeEntidad _categoria) {
		nombreFicticio =_nombreFicticio;
		operaciones = new ArrayList<Operacion>();
		etiquetas = new ArrayList<Etiqueta>();
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
	
	public void agregarEtiqueta( Etiqueta et) {
		if (!etiquetas.contains(et))
			etiquetas.add(et);
	}
	
	public void quitarEtiqueta( Etiqueta et) {
		if (etiquetas.contains(et))
			etiquetas.remove(et);
	}
	
	public List<Etiqueta> misEtiquetas(){
		return etiquetas;
	}
	
	public List<Operacion> misOperaciones(){
		return operaciones;
	}
}	
