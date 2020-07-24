package Dominio;

import java.util.ArrayList;
import java.util.List;

public class GeneradorDeReporte {
	private List<Operacion> operaciones;
	private List<Etiqueta> etiquetas;
	
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
	
	public  Reporte generarReporte() {
		 Reporte repo = new Reporte();
		 
		 for (Etiqueta e: etiquetas) {
			 for (Operacion p:operaciones) {
				 if(p.getEtiquetas().contains(e))
					 repo.agregarDetalle(e,p.getTotal());
			 }
			 
		 }
		  
		 return repo;
	}
	
	public void ingresarOperaciones(List<Operacion> operaciones) {
		for (Operacion p:operaciones) {
			this.ingresarOperacion(p);
		}
	}
}
