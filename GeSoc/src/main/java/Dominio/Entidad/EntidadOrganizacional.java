package Dominio.Entidad;

import java.util.ArrayList;
import java.util.List;

import Dominio.Etiqueta;
import Dominio.Operacion;

public class EntidadOrganizacional {

	protected String nombreFicticio;
	private List<CategoriaDeEntidad> categorias;
	private List<Operacion> operaciones;
	private List<Etiqueta> etiquetas;
	
	public EntidadOrganizacional(String _nombreFicticio) {
		nombreFicticio =_nombreFicticio;
		operaciones = new ArrayList<Operacion>();
		etiquetas = new ArrayList<Etiqueta>();
		categorias = new ArrayList<CategoriaDeEntidad>();		
	}
	
	
	public String getNombreFicticio()
	{
		return nombreFicticio;
	}
	public void setNombreFicticio( String _nombreFicticio) {
		
	}
	
	

	public void agregarOperacion(Operacion operacion) {
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
