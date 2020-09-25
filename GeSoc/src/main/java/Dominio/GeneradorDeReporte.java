package Dominio;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GeneradorDeReporte {
	private List<Operacion> operaciones;
	private List<Etiqueta> etiquetas;
	private String nombre;
	private LocalDate fecha;
	
	public GeneradorDeReporte() {
		operaciones = new ArrayList<Operacion>();
		etiquetas = new ArrayList<Etiqueta>();
	}
	  
	public void ingresarOperacion(Operacion op) {
		if (!operaciones.contains(op))
			operaciones.add(op);
	}
	
	public  void ingresarEtiqueta(Etiqueta et) {
		if (!etiquetas.contains(et))
			etiquetas.add(et);
	}
	
	public void setearNombre(String _nombre) {
		nombre = _nombre;
	}
	
	public void setearFecha(LocalDate _fecha) {
		fecha = _fecha;
	}
	
	public  Reporte generarReporte() {
		 
		 Reporte repo = new Reporte(operaciones, nombre, fecha);
		  
		 return repo;
	}
	
	public void ingresarOperaciones(List<Operacion> operaciones) {
		for (Operacion p:operaciones) {
			this.ingresarOperacion(p);
		}
	}
}
