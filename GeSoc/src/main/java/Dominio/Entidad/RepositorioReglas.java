package Dominio.Entidad;

import java.util.ArrayList;
import java.util.List;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

public class RepositorioReglas  {
	  public static RepositorioReglas instancia = new RepositorioReglas();

	    public List<ReglaDeCategoria> listar() {
	    	List<ReglaDeCategoria> lista = new ArrayList<ReglaDeCategoria>();
	    	lista.add(new BloquearAgregarEntidadBaseAJuridica());
	    	lista.add(new ImpedirQueEntidadBaseSeaParteDeJuridica());
	    	lista.add(new BloquearNuevosEgresos(10));
	        return lista;
	    }

	 
}